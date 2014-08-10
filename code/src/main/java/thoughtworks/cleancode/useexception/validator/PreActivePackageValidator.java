package thoughtworks.cleancode.useexception.validator;

import com.sun.tools.javac.code.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thoughtworks.cleancode.useexception.entity.ActiveManifest;
import thoughtworks.cleancode.useexception.entity.ActivePackageInfo;
import thoughtworks.cleancode.useexception.entity.PackageStatusPackageSoft;
import thoughtworks.cleancode.useexception.entity.PkgType;

import java.util.List;

import static thoughtworks.cleancode.useexception.entity.PkgType.BBU_SW_PATCH_PKG;
import static thoughtworks.cleancode.useexception.entity.PkgType.PATCH_PKG;
import static thoughtworks.cleancode.useexception.entity.PkgType.RRU_SW_PATCH_PKG;
import static thoughtworks.cleancode.useexception.entity.RunStatus.PACKAGE_STATUS_OF_RUN;

public class PreActivePackageValidator {
    private static final long OAM_VMP_FORBID_OPERATE_VARIOUS_PKG = 0;
    private static final long OAM_VMP_SW_TEMP_PKG = 1;
    private static final long OAM_VMP_ACTIVE_PKG_BEING_RUN = 2;
    private static final long OAM_VMP_ACTIVE_PKG_PATCH_ERROR = 3;
    private static final long OAM_VMP_SW_NO_RECORD = 4;
    private static final long OAM_VMP_EXIST_ACTIVE_BBUPKG = 5;
    private static final long NO_ERROR = -1;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ActiveManifest activeManifest;
    private List<PackageStatusPackageSoft> pkgStatusInfos;

    public long validatePreActivePkgStatus() {
        int existPkgNum = 0;
        int packageType = initialPkgType();

        for (ActivePackageInfo pkg : activeManifest.getPkgInfos()) {
            //不能同时操作多种类型的规格包
            if (isDiffpackageType(packageType, pkg)) {
                logger.error("pre active exist different type package");
                return OAM_VMP_FORBID_OPERATE_VARIOUS_PKG;
            }

            //1. 当待激活规格包存在"*.temp"包时不允许激活
            if (isTempPackage(pkg)) {
                logger.error("pre active version {} is temp package", pkg.getVersion());
                return OAM_VMP_SW_TEMP_PKG;
            }

            for (PackageStatusPackageSoft pkgStatus : pkgStatusInfos) {
                if (samePackage(pkg, pkgStatus)) {
                    //2. 待激活规格包在规格包状态描述表中存在，但规格包状态为运行时，不允许激活
                    if (pkgStatus.getRunStatus() == PACKAGE_STATUS_OF_RUN) {
                        logger.error("pre active version:{}, type:{}, product:{} package is running", pkgStatus.getVersion(), pkgStatus.getPkgType(), pkgStatus.getProductID());
                        return OAM_VMP_ACTIVE_PKG_BEING_RUN;
                    }
                    existPkgNum++;
                }

                //3. 补丁包检查，待激活补丁包时，补丁包父包不存在，父包不是运行或激活状态，不允许激活
                if (pkg.getPkgType() == BBU_SW_PATCH_PKG
                        || pkg.getPkgType() == RRU_SW_PATCH_PKG
                        || pkg.getPkgType() == PATCH_PKG) {
                    PkgType fatherPkgType = getFatherPackageType(pkg);

                    if (isFatherPkgIllegal(pkg, fatherPkgType)) {
                        logger.error("pre active version:{}, type:{}, product:{} package is running", pkgStatus.getVersion(), pkgStatus.getPkgType(), pkgStatus.getProductID());
                        return OAM_VMP_ACTIVE_PKG_PATCH_ERROR;
                    }
                }

            }

            //4. 待激活规格包在规格包状态描述表不存在，不允许激活
            if (existPkgNum != activeManifest.getPkgInfos().size()) {
                logger.error("pre active packages not all in packageStatus.xml");
                return OAM_VMP_SW_NO_RECORD;
            }

            //5. 待激活的规格包个数，比前台已存在的同类型（激活态）规格包的个数少，不允许激活
            if (activeSameTypePkgNum(packageType, pkgStatusInfos) > activeManifest.getPackageNum()) {
                logger.error("pre active packages num is less than already exist status packages number");
                return OAM_VMP_EXIST_ACTIVE_BBUPKG;
            }
        }
        return NO_ERROR;
    }

    private int activeSameTypePkgNum(int packageType, List<PackageStatusPackageSoft> pkgStatusInfos) {
        return 0;
    }

    private boolean isFatherPkgIllegal(ActivePackageInfo pkg, PkgType fatherPkgType) {
        return false;
    }

    private PkgType getFatherPackageType(ActivePackageInfo pkg) {
        return null;
    }

    private boolean samePackage(ActivePackageInfo pkg, PackageStatusPackageSoft pkgStatus) {
        return false;
    }

    private int initialPkgType() {
        return 0;
    }

    private boolean isTempPackage(ActivePackageInfo pkg) {
        return false;
    }

    private boolean isDiffpackageType(int packageType, ActivePackageInfo pkg) {
        return false;
    }
}
