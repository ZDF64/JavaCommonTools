/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package zdf.learn.com.commonUtils.data.avro.schema.can300;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Dcm19Message extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 224468750474020959L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Dcm19Message\",\"namespace\":\"zdf.learn.com.commonUtils.data.avro.schema.can300\",\"fields\":[{\"name\":\"commonHeader\",\"type\":{\"type\":\"record\",\"name\":\"CommonHeader\",\"fields\":[{\"name\":\"dataVersion\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"commandType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"destinationInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"formatInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"dcuOrDcuMeuClassification\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"dcu\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"meu\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"communicationModeFlag\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"electricPfInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"geodeticSystemInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"mapBasedVersion\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"size\",\"type\":[\"null\",\"long\"],\"default\":null}]}},{\"name\":\"updCondition\",\"type\":{\"type\":\"record\",\"name\":\"UpdCondition\",\"fields\":[{\"name\":\"updConditionId\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}},{\"name\":\"vehicleInformationHeader\",\"type\":{\"type\":\"record\",\"name\":\"VehicleInformationHeader\",\"fields\":[{\"name\":\"dataCapacityNumber\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"timeZoneOffset\",\"type\":[\"null\",\"int\"],\"default\":null}],\"default\":null}},{\"name\":\"dataCapacityList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"DataCapacity\",\"fields\":[{\"name\":\"timeAndCoordinate\",\"type\":{\"type\":\"record\",\"name\":\"TimeAndCoordinate\",\"fields\":[{\"name\":\"gps\",\"type\":{\"type\":\"record\",\"name\":\"GPS\",\"fields\":[{\"name\":\"gpsDate\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"point\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Point\",\"fields\":[{\"name\":\"latitude\",\"type\":[\"null\",\"double\"],\"default\":null},{\"name\":\"longitude\",\"type\":[\"null\",\"double\"],\"default\":null}]}]},{\"name\":\"pdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"hdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"vdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"measureCount\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}},{\"name\":\"mm\",\"type\":{\"type\":\"record\",\"name\":\"MM\",\"fields\":[{\"name\":\"point\",\"type\":[\"null\",\"Point\"],\"default\":null},{\"name\":\"rticLinkId\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}}],\"default\":null}},{\"name\":\"canFrameNumber\",\"type\":{\"type\":\"record\",\"name\":\"CanFrameNumber\",\"fields\":[{\"name\":\"dataCapacityType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"dataCapacityNumber\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}},{\"name\":\"canInformationList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"CanInformationList\",\"fields\":[{\"name\":\"canId\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"dataLengthAfterCompression\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"collectType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"canType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"time\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"outsideUseDataMap\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}],\"default\":null}],\"default\":null},\"java-class\":\"java.util.List\"}},{\"name\":\"type3OutsideUseData\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Type3OutsideUseData\",\"fields\":[{\"name\":\"dateTime\",\"type\":\"string\"},{\"name\":\"label\",\"type\":\"string\"},{\"name\":\"value\",\"type\":\"string\"},{\"name\":\"unit\",\"type\":\"string\"}],\"default\":null},\"java-class\":\"java.util.List\"}}],\"default\":null},\"java-class\":\"java.util.List\"}}],\"default\":null}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Dcm19Message> ENCODER =
      new BinaryMessageEncoder<Dcm19Message>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Dcm19Message> DECODER =
      new BinaryMessageDecoder<Dcm19Message>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Dcm19Message> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Dcm19Message> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Dcm19Message>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Dcm19Message to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Dcm19Message from a ByteBuffer. */
  public static Dcm19Message fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader commonHeader;
  @Deprecated public zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition updCondition;
  @Deprecated public zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader vehicleInformationHeader;
  @Deprecated public java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity> dataCapacityList;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Dcm19Message() {}

  /**
   * All-args constructor.
   * @param commonHeader The new value for commonHeader
   * @param updCondition The new value for updCondition
   * @param vehicleInformationHeader The new value for vehicleInformationHeader
   * @param dataCapacityList The new value for dataCapacityList
   */
  public Dcm19Message(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader commonHeader, zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition updCondition, zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader vehicleInformationHeader, java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity> dataCapacityList) {
    this.commonHeader = commonHeader;
    this.updCondition = updCondition;
    this.vehicleInformationHeader = vehicleInformationHeader;
    this.dataCapacityList = dataCapacityList;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return commonHeader;
    case 1: return updCondition;
    case 2: return vehicleInformationHeader;
    case 3: return dataCapacityList;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: commonHeader = (zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader)value$; break;
    case 1: updCondition = (zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition)value$; break;
    case 2: vehicleInformationHeader = (zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader)value$; break;
    case 3: dataCapacityList = (java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'commonHeader' field.
   * @return The value of the 'commonHeader' field.
   */
  public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader getCommonHeader() {
    return commonHeader;
  }

  /**
   * Sets the value of the 'commonHeader' field.
   * @param value the value to set.
   */
  public void setCommonHeader(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader value) {
    this.commonHeader = value;
  }

  /**
   * Gets the value of the 'updCondition' field.
   * @return The value of the 'updCondition' field.
   */
  public zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition getUpdCondition() {
    return updCondition;
  }

  /**
   * Sets the value of the 'updCondition' field.
   * @param value the value to set.
   */
  public void setUpdCondition(zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition value) {
    this.updCondition = value;
  }

  /**
   * Gets the value of the 'vehicleInformationHeader' field.
   * @return The value of the 'vehicleInformationHeader' field.
   */
  public zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader getVehicleInformationHeader() {
    return vehicleInformationHeader;
  }

  /**
   * Sets the value of the 'vehicleInformationHeader' field.
   * @param value the value to set.
   */
  public void setVehicleInformationHeader(zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader value) {
    this.vehicleInformationHeader = value;
  }

  /**
   * Gets the value of the 'dataCapacityList' field.
   * @return The value of the 'dataCapacityList' field.
   */
  public java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity> getDataCapacityList() {
    return dataCapacityList;
  }

  /**
   * Sets the value of the 'dataCapacityList' field.
   * @param value the value to set.
   */
  public void setDataCapacityList(java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity> value) {
    this.dataCapacityList = value;
  }

  /**
   * Creates a new Dcm19Message RecordBuilder.
   * @return A new Dcm19Message RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder newBuilder() {
    return new zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder();
  }

  /**
   * Creates a new Dcm19Message RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Dcm19Message RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder newBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder other) {
    return new zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder(other);
  }

  /**
   * Creates a new Dcm19Message RecordBuilder by copying an existing Dcm19Message instance.
   * @param other The existing instance to copy.
   * @return A new Dcm19Message RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder newBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message other) {
    return new zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder(other);
  }

  /**
   * RecordBuilder for Dcm19Message instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Dcm19Message>
    implements org.apache.avro.data.RecordBuilder<Dcm19Message> {

    private zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader commonHeader;
    private zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder commonHeaderBuilder;
    private zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition updCondition;
    private zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder updConditionBuilder;
    private zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader vehicleInformationHeader;
    private zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader.Builder vehicleInformationHeaderBuilder;
    private java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity> dataCapacityList;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.commonHeader)) {
        this.commonHeader = data().deepCopy(fields()[0].schema(), other.commonHeader);
        fieldSetFlags()[0] = true;
      }
      if (other.hasCommonHeaderBuilder()) {
        this.commonHeaderBuilder = zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.newBuilder(other.getCommonHeaderBuilder());
      }
      if (isValidValue(fields()[1], other.updCondition)) {
        this.updCondition = data().deepCopy(fields()[1].schema(), other.updCondition);
        fieldSetFlags()[1] = true;
      }
      if (other.hasUpdConditionBuilder()) {
        this.updConditionBuilder = zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.newBuilder(other.getUpdConditionBuilder());
      }
      if (isValidValue(fields()[2], other.vehicleInformationHeader)) {
        this.vehicleInformationHeader = data().deepCopy(fields()[2].schema(), other.vehicleInformationHeader);
        fieldSetFlags()[2] = true;
      }
      if (other.hasVehicleInformationHeaderBuilder()) {
        this.vehicleInformationHeaderBuilder = zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader.newBuilder(other.getVehicleInformationHeaderBuilder());
      }
      if (isValidValue(fields()[3], other.dataCapacityList)) {
        this.dataCapacityList = data().deepCopy(fields()[3].schema(), other.dataCapacityList);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Dcm19Message instance
     * @param other The existing instance to copy.
     */
    private Builder(zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.commonHeader)) {
        this.commonHeader = data().deepCopy(fields()[0].schema(), other.commonHeader);
        fieldSetFlags()[0] = true;
      }
      this.commonHeaderBuilder = null;
      if (isValidValue(fields()[1], other.updCondition)) {
        this.updCondition = data().deepCopy(fields()[1].schema(), other.updCondition);
        fieldSetFlags()[1] = true;
      }
      this.updConditionBuilder = null;
      if (isValidValue(fields()[2], other.vehicleInformationHeader)) {
        this.vehicleInformationHeader = data().deepCopy(fields()[2].schema(), other.vehicleInformationHeader);
        fieldSetFlags()[2] = true;
      }
      this.vehicleInformationHeaderBuilder = null;
      if (isValidValue(fields()[3], other.dataCapacityList)) {
        this.dataCapacityList = data().deepCopy(fields()[3].schema(), other.dataCapacityList);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'commonHeader' field.
      * @return The value.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader getCommonHeader() {
      return commonHeader;
    }

    /**
      * Sets the value of the 'commonHeader' field.
      * @param value The value of 'commonHeader'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder setCommonHeader(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader value) {
      validate(fields()[0], value);
      this.commonHeaderBuilder = null;
      this.commonHeader = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'commonHeader' field has been set.
      * @return True if the 'commonHeader' field has been set, false otherwise.
      */
    public boolean hasCommonHeader() {
      return fieldSetFlags()[0];
    }

    /**
     * Gets the Builder instance for the 'commonHeader' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder getCommonHeaderBuilder() {
      if (commonHeaderBuilder == null) {
        if (hasCommonHeader()) {
          setCommonHeaderBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.newBuilder(commonHeader));
        } else {
          setCommonHeaderBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.newBuilder());
        }
      }
      return commonHeaderBuilder;
    }

    /**
     * Sets the Builder instance for the 'commonHeader' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder setCommonHeaderBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder value) {
      clearCommonHeader();
      commonHeaderBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'commonHeader' field has an active Builder instance
     * @return True if the 'commonHeader' field has an active Builder instance
     */
    public boolean hasCommonHeaderBuilder() {
      return commonHeaderBuilder != null;
    }

    /**
      * Clears the value of the 'commonHeader' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder clearCommonHeader() {
      commonHeader = null;
      commonHeaderBuilder = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'updCondition' field.
      * @return The value.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition getUpdCondition() {
      return updCondition;
    }

    /**
      * Sets the value of the 'updCondition' field.
      * @param value The value of 'updCondition'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder setUpdCondition(zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition value) {
      validate(fields()[1], value);
      this.updConditionBuilder = null;
      this.updCondition = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'updCondition' field has been set.
      * @return True if the 'updCondition' field has been set, false otherwise.
      */
    public boolean hasUpdCondition() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'updCondition' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder getUpdConditionBuilder() {
      if (updConditionBuilder == null) {
        if (hasUpdCondition()) {
          setUpdConditionBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.newBuilder(updCondition));
        } else {
          setUpdConditionBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.newBuilder());
        }
      }
      return updConditionBuilder;
    }

    /**
     * Sets the Builder instance for the 'updCondition' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder setUpdConditionBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder value) {
      clearUpdCondition();
      updConditionBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'updCondition' field has an active Builder instance
     * @return True if the 'updCondition' field has an active Builder instance
     */
    public boolean hasUpdConditionBuilder() {
      return updConditionBuilder != null;
    }

    /**
      * Clears the value of the 'updCondition' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder clearUpdCondition() {
      updCondition = null;
      updConditionBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'vehicleInformationHeader' field.
      * @return The value.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader getVehicleInformationHeader() {
      return vehicleInformationHeader;
    }

    /**
      * Sets the value of the 'vehicleInformationHeader' field.
      * @param value The value of 'vehicleInformationHeader'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder setVehicleInformationHeader(zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader value) {
      validate(fields()[2], value);
      this.vehicleInformationHeaderBuilder = null;
      this.vehicleInformationHeader = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'vehicleInformationHeader' field has been set.
      * @return True if the 'vehicleInformationHeader' field has been set, false otherwise.
      */
    public boolean hasVehicleInformationHeader() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'vehicleInformationHeader' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader.Builder getVehicleInformationHeaderBuilder() {
      if (vehicleInformationHeaderBuilder == null) {
        if (hasVehicleInformationHeader()) {
          setVehicleInformationHeaderBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader.newBuilder(vehicleInformationHeader));
        } else {
          setVehicleInformationHeaderBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader.newBuilder());
        }
      }
      return vehicleInformationHeaderBuilder;
    }

    /**
     * Sets the Builder instance for the 'vehicleInformationHeader' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder setVehicleInformationHeaderBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader.Builder value) {
      clearVehicleInformationHeader();
      vehicleInformationHeaderBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'vehicleInformationHeader' field has an active Builder instance
     * @return True if the 'vehicleInformationHeader' field has an active Builder instance
     */
    public boolean hasVehicleInformationHeaderBuilder() {
      return vehicleInformationHeaderBuilder != null;
    }

    /**
      * Clears the value of the 'vehicleInformationHeader' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder clearVehicleInformationHeader() {
      vehicleInformationHeader = null;
      vehicleInformationHeaderBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'dataCapacityList' field.
      * @return The value.
      */
    public java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity> getDataCapacityList() {
      return dataCapacityList;
    }

    /**
      * Sets the value of the 'dataCapacityList' field.
      * @param value The value of 'dataCapacityList'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder setDataCapacityList(java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity> value) {
      validate(fields()[3], value);
      this.dataCapacityList = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'dataCapacityList' field has been set.
      * @return True if the 'dataCapacityList' field has been set, false otherwise.
      */
    public boolean hasDataCapacityList() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'dataCapacityList' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message.Builder clearDataCapacityList() {
      dataCapacityList = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Dcm19Message build() {
      try {
        Dcm19Message record = new Dcm19Message();
        if (commonHeaderBuilder != null) {
          record.commonHeader = this.commonHeaderBuilder.build();
        } else {
          record.commonHeader = fieldSetFlags()[0] ? this.commonHeader : (zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader) defaultValue(fields()[0]);
        }
        if (updConditionBuilder != null) {
          record.updCondition = this.updConditionBuilder.build();
        } else {
          record.updCondition = fieldSetFlags()[1] ? this.updCondition : (zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition) defaultValue(fields()[1]);
        }
        if (vehicleInformationHeaderBuilder != null) {
          record.vehicleInformationHeader = this.vehicleInformationHeaderBuilder.build();
        } else {
          record.vehicleInformationHeader = fieldSetFlags()[2] ? this.vehicleInformationHeader : (zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader) defaultValue(fields()[2]);
        }
        record.dataCapacityList = fieldSetFlags()[3] ? this.dataCapacityList : (java.util.List<zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity>) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Dcm19Message>
    WRITER$ = (org.apache.avro.io.DatumWriter<Dcm19Message>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Dcm19Message>
    READER$ = (org.apache.avro.io.DatumReader<Dcm19Message>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
