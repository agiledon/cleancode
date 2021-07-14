/**
 *
 */
package zhangyi.cleancode.alarmparser;

/**
 *
 *
 */
public interface FmAlarmXMLFeatures {
    /** Namespaces feature id (http://xml.org/sax/features/namespaces). */
    String NAMESPACES_FEATURE_ID = "http://xml.org/sax/features/namespaces";

    /** Validation feature id (http://xml.org/sax/features/validation). */
    String VALIDATION_FEATURE_ID = "http://xml.org/sax/features/validation";

    /**
     * Schema validation feature id (http://apache.org/xml/features/validation/schema).
     */
    String SCHEMA_VALIDATION_FEATURE_ID = "http://apache.org/xml/features/validation/schema";

    /**
     * Schema full checking feature id (http://apache.org/xml/features/validation/schema-full-checking).
     */
    String SCHEMA_FULL_CHECKING_FEATURE_ID = "http://apache.org/xml/features/validation/schema-full-checking";

    /**
     * Dynamic validation feature id (http://apache.org/xml/features/validation/dynamic).
     */
    String DYNAMIC_VALIDATION_FEATURE_ID = "http://apache.org/xml/features/validation/dynamic";

    /**
     * Load external DTD feature id (http://apache.org/xml/features/nonvalidating/load-external-dtd).
     */
    String LOAD_EXTERNAL_DTD_FEATURE_ID = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

    // default settings

    /** Default parser name. */
    String DEFAULT_PARSER_NAME = "dom.wrappers.Xerces";

    /** Default namespaces support (true). */
    boolean DEFAULT_NAMESPACES = true;

    /** Default validation support (false). */
    boolean DEFAULT_VALIDATION = true;

    /** Default load external DTD (true). */
    boolean DEFAULT_LOAD_EXTERNAL_DTD = true;

    /** Default Schema validation support (false). */
    boolean DEFAULT_SCHEMA_VALIDATION = true;

    /** Default Schema full checking support (false). */
    boolean DEFAULT_SCHEMA_FULL_CHECKING = true;

    /** Default dynamic validation support (false). */
    boolean DEFAULT_DYNAMIC_VALIDATION = true;

    /** Default canonical output (false). */
    boolean DEFAULT_CANONICAL = false;

}
