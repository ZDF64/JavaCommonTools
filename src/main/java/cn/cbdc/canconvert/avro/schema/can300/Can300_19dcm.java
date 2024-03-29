/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package cn.cbdc.canconvert.avro.schema.can300;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Can300_19dcm extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4020365242054298241L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Can300_19dcm\",\"namespace\":\"cn.cbdc.canconvert.avro.schema.can300\",\"fields\":[{\"name\":\"headers\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"string\"}],\"default\":null},{\"name\":\"correlationId\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"dispatchModelType\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"groupNumber\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"vehicleName\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"body\",\"type\":{\"type\":\"record\",\"name\":\"Dcm19Message\",\"fields\":[{\"name\":\"commonHeader\",\"type\":{\"type\":\"record\",\"name\":\"CommonHeader\",\"fields\":[{\"name\":\"dataVersion\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"commandType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"destinationInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"formatInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"dcuOrDcuMeuClassification\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"dcu\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"meu\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"communicationModeFlag\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"electricPfInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"geodeticSystemInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"mapBasedVersion\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"size\",\"type\":[\"null\",\"long\"],\"default\":null}]}},{\"name\":\"updCondition\",\"type\":{\"type\":\"record\",\"name\":\"UpdCondition\",\"fields\":[{\"name\":\"updConditionId\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}},{\"name\":\"vehicleInformationHeader\",\"type\":{\"type\":\"record\",\"name\":\"VehicleInformationHeader\",\"fields\":[{\"name\":\"dataCapacityNumber\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"timeZoneOffset\",\"type\":[\"null\",\"int\"],\"default\":null}],\"default\":null}},{\"name\":\"dataCapacityList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"DataCapacity\",\"fields\":[{\"name\":\"timeAndCoordinate\",\"type\":{\"type\":\"record\",\"name\":\"TimeAndCoordinate\",\"fields\":[{\"name\":\"gps\",\"type\":{\"type\":\"record\",\"name\":\"GPS\",\"fields\":[{\"name\":\"gpsDate\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"point\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Point\",\"fields\":[{\"name\":\"latitude\",\"type\":[\"null\",\"double\"],\"default\":null},{\"name\":\"longitude\",\"type\":[\"null\",\"double\"],\"default\":null}]}]},{\"name\":\"pdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"hdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"vdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"measureCount\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}},{\"name\":\"mm\",\"type\":{\"type\":\"record\",\"name\":\"MM\",\"fields\":[{\"name\":\"point\",\"type\":[\"null\",\"Point\"],\"default\":null},{\"name\":\"rticLinkId\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}}],\"default\":null}},{\"name\":\"canFrameNumber\",\"type\":{\"type\":\"record\",\"name\":\"CanFrameNumber\",\"fields\":[{\"name\":\"dataCapacityType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"dataCapacityNumber\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}},{\"name\":\"canInformationList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"CanInformation\",\"fields\":[{\"name\":\"canId\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"dataLengthAfterCompression\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"time\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"outsideUseDataMap\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}],\"default\":null}],\"default\":null},\"java-class\":\"java.util.List\"}},{\"name\":\"type3OutsideUseData\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Type3OutsideUseData\",\"fields\":[{\"name\":\"dateTime\",\"type\":\"string\"},{\"name\":\"label\",\"type\":\"string\"},{\"name\":\"value\",\"type\":\"string\"},{\"name\":\"unit\",\"type\":\"string\"}],\"default\":null},\"java-class\":\"java.util.List\"}}],\"default\":null},\"java-class\":\"java.util.List\"}}],\"default\":null}},{\"name\":\"decoderWarningList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"DecoderWarningList\",\"namespace\":\"cn.cbdc.canconvert.avro.schema.can300_19nev\",\"fields\":[{\"name\":\"message\",\"type\":\"string\"},{\"name\":\"type\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"DecodeWarningStatus\",\"symbols\":[\"OUT_OF_RANGE\",\"ABNORMAL\",\"INVALID\",\"UNSETTLED\",\"INDETERMINATE\",\"UNIT_ERROR\",\"NULL_OR_INVALID\",\"VERSION_ERROR\",\"UNCORRECTION\",\"ABNORMAL_INVALID\",\"DEFAULT_NOT_SET\",\"CALCULATE_ERROR\"]}]}],\"default\":null},\"java-class\":\"java.util.List\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Can300_19dcm> ENCODER =
      new BinaryMessageEncoder<Can300_19dcm>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Can300_19dcm> DECODER =
      new BinaryMessageDecoder<Can300_19dcm>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Can300_19dcm> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Can300_19dcm> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Can300_19dcm>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Can300_19dcm to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Can300_19dcm from a ByteBuffer. */
  public static Can300_19dcm fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> headers;
  @Deprecated public java.lang.CharSequence correlationId;
  @Deprecated public java.lang.CharSequence dispatchModelType;
  @Deprecated public java.lang.CharSequence groupNumber;
  @Deprecated public java.lang.CharSequence vehicleName;
  @Deprecated public cn.cbdc.canconvert.avro.schema.can300.Dcm19Message body;
  @Deprecated public java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList> decoderWarningList;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Can300_19dcm() {}

  /**
   * All-args constructor.
   * @param headers The new value for headers
   * @param correlationId The new value for correlationId
   * @param dispatchModelType The new value for dispatchModelType
   * @param groupNumber The new value for groupNumber
   * @param vehicleName The new value for vehicleName
   * @param body The new value for body
   * @param decoderWarningList The new value for decoderWarningList
   */
  public Can300_19dcm(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> headers, java.lang.CharSequence correlationId, java.lang.CharSequence dispatchModelType, java.lang.CharSequence groupNumber, java.lang.CharSequence vehicleName, cn.cbdc.canconvert.avro.schema.can300.Dcm19Message body, java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList> decoderWarningList) {
    this.headers = headers;
    this.correlationId = correlationId;
    this.dispatchModelType = dispatchModelType;
    this.groupNumber = groupNumber;
    this.vehicleName = vehicleName;
    this.body = body;
    this.decoderWarningList = decoderWarningList;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return headers;
    case 1: return correlationId;
    case 2: return dispatchModelType;
    case 3: return groupNumber;
    case 4: return vehicleName;
    case 5: return body;
    case 6: return decoderWarningList;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: headers = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)value$; break;
    case 1: correlationId = (java.lang.CharSequence)value$; break;
    case 2: dispatchModelType = (java.lang.CharSequence)value$; break;
    case 3: groupNumber = (java.lang.CharSequence)value$; break;
    case 4: vehicleName = (java.lang.CharSequence)value$; break;
    case 5: body = (cn.cbdc.canconvert.avro.schema.can300.Dcm19Message)value$; break;
    case 6: decoderWarningList = (java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'headers' field.
   * @return The value of the 'headers' field.
   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getHeaders() {
    return headers;
  }

  /**
   * Sets the value of the 'headers' field.
   * @param value the value to set.
   */
  public void setHeaders(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.headers = value;
  }

  /**
   * Gets the value of the 'correlationId' field.
   * @return The value of the 'correlationId' field.
   */
  public java.lang.CharSequence getCorrelationId() {
    return correlationId;
  }

  /**
   * Sets the value of the 'correlationId' field.
   * @param value the value to set.
   */
  public void setCorrelationId(java.lang.CharSequence value) {
    this.correlationId = value;
  }

  /**
   * Gets the value of the 'dispatchModelType' field.
   * @return The value of the 'dispatchModelType' field.
   */
  public java.lang.CharSequence getDispatchModelType() {
    return dispatchModelType;
  }

  /**
   * Sets the value of the 'dispatchModelType' field.
   * @param value the value to set.
   */
  public void setDispatchModelType(java.lang.CharSequence value) {
    this.dispatchModelType = value;
  }

  /**
   * Gets the value of the 'groupNumber' field.
   * @return The value of the 'groupNumber' field.
   */
  public java.lang.CharSequence getGroupNumber() {
    return groupNumber;
  }

  /**
   * Sets the value of the 'groupNumber' field.
   * @param value the value to set.
   */
  public void setGroupNumber(java.lang.CharSequence value) {
    this.groupNumber = value;
  }

  /**
   * Gets the value of the 'vehicleName' field.
   * @return The value of the 'vehicleName' field.
   */
  public java.lang.CharSequence getVehicleName() {
    return vehicleName;
  }

  /**
   * Sets the value of the 'vehicleName' field.
   * @param value the value to set.
   */
  public void setVehicleName(java.lang.CharSequence value) {
    this.vehicleName = value;
  }

  /**
   * Gets the value of the 'body' field.
   * @return The value of the 'body' field.
   */
  public cn.cbdc.canconvert.avro.schema.can300.Dcm19Message getBody() {
    return body;
  }

  /**
   * Sets the value of the 'body' field.
   * @param value the value to set.
   */
  public void setBody(cn.cbdc.canconvert.avro.schema.can300.Dcm19Message value) {
    this.body = value;
  }

  /**
   * Gets the value of the 'decoderWarningList' field.
   * @return The value of the 'decoderWarningList' field.
   */
  public java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList> getDecoderWarningList() {
    return decoderWarningList;
  }

  /**
   * Sets the value of the 'decoderWarningList' field.
   * @param value the value to set.
   */
  public void setDecoderWarningList(java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList> value) {
    this.decoderWarningList = value;
  }

  /**
   * Creates a new Can300_19dcm RecordBuilder.
   * @return A new Can300_19dcm RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder newBuilder() {
    return new cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder();
  }

  /**
   * Creates a new Can300_19dcm RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Can300_19dcm RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder newBuilder(cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder other) {
    return new cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder(other);
  }

  /**
   * Creates a new Can300_19dcm RecordBuilder by copying an existing Can300_19dcm instance.
   * @param other The existing instance to copy.
   * @return A new Can300_19dcm RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder newBuilder(cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm other) {
    return new cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder(other);
  }

  /**
   * RecordBuilder for Can300_19dcm instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Can300_19dcm>
    implements org.apache.avro.data.RecordBuilder<Can300_19dcm> {

    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> headers;
    private java.lang.CharSequence correlationId;
    private java.lang.CharSequence dispatchModelType;
    private java.lang.CharSequence groupNumber;
    private java.lang.CharSequence vehicleName;
    private cn.cbdc.canconvert.avro.schema.can300.Dcm19Message body;
    private cn.cbdc.canconvert.avro.schema.can300.Dcm19Message.Builder bodyBuilder;
    private java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList> decoderWarningList;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.headers)) {
        this.headers = data().deepCopy(fields()[0].schema(), other.headers);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.correlationId)) {
        this.correlationId = data().deepCopy(fields()[1].schema(), other.correlationId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.dispatchModelType)) {
        this.dispatchModelType = data().deepCopy(fields()[2].schema(), other.dispatchModelType);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.groupNumber)) {
        this.groupNumber = data().deepCopy(fields()[3].schema(), other.groupNumber);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.vehicleName)) {
        this.vehicleName = data().deepCopy(fields()[4].schema(), other.vehicleName);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.body)) {
        this.body = data().deepCopy(fields()[5].schema(), other.body);
        fieldSetFlags()[5] = true;
      }
      if (other.hasBodyBuilder()) {
        this.bodyBuilder = cn.cbdc.canconvert.avro.schema.can300.Dcm19Message.newBuilder(other.getBodyBuilder());
      }
      if (isValidValue(fields()[6], other.decoderWarningList)) {
        this.decoderWarningList = data().deepCopy(fields()[6].schema(), other.decoderWarningList);
        fieldSetFlags()[6] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Can300_19dcm instance
     * @param other The existing instance to copy.
     */
    private Builder(cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.headers)) {
        this.headers = data().deepCopy(fields()[0].schema(), other.headers);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.correlationId)) {
        this.correlationId = data().deepCopy(fields()[1].schema(), other.correlationId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.dispatchModelType)) {
        this.dispatchModelType = data().deepCopy(fields()[2].schema(), other.dispatchModelType);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.groupNumber)) {
        this.groupNumber = data().deepCopy(fields()[3].schema(), other.groupNumber);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.vehicleName)) {
        this.vehicleName = data().deepCopy(fields()[4].schema(), other.vehicleName);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.body)) {
        this.body = data().deepCopy(fields()[5].schema(), other.body);
        fieldSetFlags()[5] = true;
      }
      this.bodyBuilder = null;
      if (isValidValue(fields()[6], other.decoderWarningList)) {
        this.decoderWarningList = data().deepCopy(fields()[6].schema(), other.decoderWarningList);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'headers' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getHeaders() {
      return headers;
    }

    /**
      * Sets the value of the 'headers' field.
      * @param value The value of 'headers'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder setHeaders(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[0], value);
      this.headers = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'headers' field has been set.
      * @return True if the 'headers' field has been set, false otherwise.
      */
    public boolean hasHeaders() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'headers' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder clearHeaders() {
      headers = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'correlationId' field.
      * @return The value.
      */
    public java.lang.CharSequence getCorrelationId() {
      return correlationId;
    }

    /**
      * Sets the value of the 'correlationId' field.
      * @param value The value of 'correlationId'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder setCorrelationId(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.correlationId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'correlationId' field has been set.
      * @return True if the 'correlationId' field has been set, false otherwise.
      */
    public boolean hasCorrelationId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'correlationId' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder clearCorrelationId() {
      correlationId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'dispatchModelType' field.
      * @return The value.
      */
    public java.lang.CharSequence getDispatchModelType() {
      return dispatchModelType;
    }

    /**
      * Sets the value of the 'dispatchModelType' field.
      * @param value The value of 'dispatchModelType'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder setDispatchModelType(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.dispatchModelType = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'dispatchModelType' field has been set.
      * @return True if the 'dispatchModelType' field has been set, false otherwise.
      */
    public boolean hasDispatchModelType() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'dispatchModelType' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder clearDispatchModelType() {
      dispatchModelType = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'groupNumber' field.
      * @return The value.
      */
    public java.lang.CharSequence getGroupNumber() {
      return groupNumber;
    }

    /**
      * Sets the value of the 'groupNumber' field.
      * @param value The value of 'groupNumber'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder setGroupNumber(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.groupNumber = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'groupNumber' field has been set.
      * @return True if the 'groupNumber' field has been set, false otherwise.
      */
    public boolean hasGroupNumber() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'groupNumber' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder clearGroupNumber() {
      groupNumber = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'vehicleName' field.
      * @return The value.
      */
    public java.lang.CharSequence getVehicleName() {
      return vehicleName;
    }

    /**
      * Sets the value of the 'vehicleName' field.
      * @param value The value of 'vehicleName'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder setVehicleName(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.vehicleName = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'vehicleName' field has been set.
      * @return True if the 'vehicleName' field has been set, false otherwise.
      */
    public boolean hasVehicleName() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'vehicleName' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder clearVehicleName() {
      vehicleName = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'body' field.
      * @return The value.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Dcm19Message getBody() {
      return body;
    }

    /**
      * Sets the value of the 'body' field.
      * @param value The value of 'body'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder setBody(cn.cbdc.canconvert.avro.schema.can300.Dcm19Message value) {
      validate(fields()[5], value);
      this.bodyBuilder = null;
      this.body = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'body' field has been set.
      * @return True if the 'body' field has been set, false otherwise.
      */
    public boolean hasBody() {
      return fieldSetFlags()[5];
    }

    /**
     * Gets the Builder instance for the 'body' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public cn.cbdc.canconvert.avro.schema.can300.Dcm19Message.Builder getBodyBuilder() {
      if (bodyBuilder == null) {
        if (hasBody()) {
          setBodyBuilder(cn.cbdc.canconvert.avro.schema.can300.Dcm19Message.newBuilder(body));
        } else {
          setBodyBuilder(cn.cbdc.canconvert.avro.schema.can300.Dcm19Message.newBuilder());
        }
      }
      return bodyBuilder;
    }

    /**
     * Sets the Builder instance for the 'body' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder setBodyBuilder(cn.cbdc.canconvert.avro.schema.can300.Dcm19Message.Builder value) {
      clearBody();
      bodyBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'body' field has an active Builder instance
     * @return True if the 'body' field has an active Builder instance
     */
    public boolean hasBodyBuilder() {
      return bodyBuilder != null;
    }

    /**
      * Clears the value of the 'body' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder clearBody() {
      body = null;
      bodyBuilder = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'decoderWarningList' field.
      * @return The value.
      */
    public java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList> getDecoderWarningList() {
      return decoderWarningList;
    }

    /**
      * Sets the value of the 'decoderWarningList' field.
      * @param value The value of 'decoderWarningList'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder setDecoderWarningList(java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList> value) {
      validate(fields()[6], value);
      this.decoderWarningList = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'decoderWarningList' field has been set.
      * @return True if the 'decoderWarningList' field has been set, false otherwise.
      */
    public boolean hasDecoderWarningList() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'decoderWarningList' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm.Builder clearDecoderWarningList() {
      decoderWarningList = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Can300_19dcm build() {
      try {
        Can300_19dcm record = new Can300_19dcm();
        record.headers = fieldSetFlags()[0] ? this.headers : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) defaultValue(fields()[0]);
        record.correlationId = fieldSetFlags()[1] ? this.correlationId : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.dispatchModelType = fieldSetFlags()[2] ? this.dispatchModelType : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.groupNumber = fieldSetFlags()[3] ? this.groupNumber : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.vehicleName = fieldSetFlags()[4] ? this.vehicleName : (java.lang.CharSequence) defaultValue(fields()[4]);
        if (bodyBuilder != null) {
          record.body = this.bodyBuilder.build();
        } else {
          record.body = fieldSetFlags()[5] ? this.body : (cn.cbdc.canconvert.avro.schema.can300.Dcm19Message) defaultValue(fields()[5]);
        }
        record.decoderWarningList = fieldSetFlags()[6] ? this.decoderWarningList : (java.util.List<cn.cbdc.canconvert.avro.schema.can300_19nev.DecoderWarningList>) defaultValue(fields()[6]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Can300_19dcm>
    WRITER$ = (org.apache.avro.io.DatumWriter<Can300_19dcm>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Can300_19dcm>
    READER$ = (org.apache.avro.io.DatumReader<Can300_19dcm>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
