/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package zdf.learn.com.commonUtils.data.avro.schema.can300_19nev;
@org.apache.avro.specific.AvroGenerated
public enum DecodeWarningStatus implements org.apache.avro.generic.GenericEnumSymbol<DecodeWarningStatus> {
  OUT_OF_RANGE, ABNORMAL, INVALID, UNSETTLED, INDETERMINATE, UNIT_ERROR, NULL_OR_INVALID, VERSION_ERROR, UNCORRECTION, ABNORMAL_INVALID, DEFAULT_NOT_SET, CALCULATE_ERROR  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"DecodeWarningStatus\",\"namespace\":\"zdf.learn.com.commonUtils.data.avro.schema.can300_19nev\",\"symbols\":[\"OUT_OF_RANGE\",\"ABNORMAL\",\"INVALID\",\"UNSETTLED\",\"INDETERMINATE\",\"UNIT_ERROR\",\"NULL_OR_INVALID\",\"VERSION_ERROR\",\"UNCORRECTION\",\"ABNORMAL_INVALID\",\"DEFAULT_NOT_SET\",\"CALCULATE_ERROR\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
