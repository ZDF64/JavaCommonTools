/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package cn.cbdc.canconvert.avro.schema.can300_19nev;
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public enum DecodeWarningStatus {
  OUT_OF_RANGE, ABNORMAL, INVALID, UNSETTLED, INDETERMINATE, UNIT_ERROR, NULL_OR_INVALID, VERSION_ERROR, UNCORRECTION, ABNORMAL_INVALID, DEFAULT_NOT_SET, CALCULATE_ERROR  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"DecodeWarningStatus\",\"namespace\":\"cn.cbdc.canconvert.avro.schema.can300_19nev\",\"symbols\":[\"OUT_OF_RANGE\",\"ABNORMAL\",\"INVALID\",\"UNSETTLED\",\"INDETERMINATE\",\"UNIT_ERROR\",\"NULL_OR_INVALID\",\"VERSION_ERROR\",\"UNCORRECTION\",\"ABNORMAL_INVALID\",\"DEFAULT_NOT_SET\",\"CALCULATE_ERROR\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
}