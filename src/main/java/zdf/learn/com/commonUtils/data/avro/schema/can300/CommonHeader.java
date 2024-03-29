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
public class CommonHeader extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 964724047079396816L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CommonHeader\",\"namespace\":\"zdf.learn.com.commonUtils.data.avro.schema.can300\",\"fields\":[{\"name\":\"dataVersion\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"commandType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"destinationInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"formatInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"dcuOrDcuMeuClassification\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"dcu\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"meu\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"communicationModeFlag\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"electricPfInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"geodeticSystemInformation\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"mapBasedVersion\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"size\",\"type\":[\"null\",\"long\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<CommonHeader> ENCODER =
      new BinaryMessageEncoder<CommonHeader>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CommonHeader> DECODER =
      new BinaryMessageDecoder<CommonHeader>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<CommonHeader> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<CommonHeader> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<CommonHeader>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this CommonHeader to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a CommonHeader from a ByteBuffer. */
  public static CommonHeader fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence dataVersion;
  @Deprecated public java.lang.Long commandType;
  @Deprecated public java.lang.Long destinationInformation;
  @Deprecated public java.lang.Long formatInformation;
  @Deprecated public java.lang.Long dcuOrDcuMeuClassification;
  @Deprecated public java.lang.Long dcu;
  @Deprecated public java.lang.Long meu;
  @Deprecated public java.lang.Long communicationModeFlag;
  @Deprecated public java.lang.Long electricPfInformation;
  @Deprecated public java.lang.Long geodeticSystemInformation;
  @Deprecated public java.lang.Long mapBasedVersion;
  @Deprecated public java.lang.Long size;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CommonHeader() {}

  /**
   * All-args constructor.
   * @param dataVersion The new value for dataVersion
   * @param commandType The new value for commandType
   * @param destinationInformation The new value for destinationInformation
   * @param formatInformation The new value for formatInformation
   * @param dcuOrDcuMeuClassification The new value for dcuOrDcuMeuClassification
   * @param dcu The new value for dcu
   * @param meu The new value for meu
   * @param communicationModeFlag The new value for communicationModeFlag
   * @param electricPfInformation The new value for electricPfInformation
   * @param geodeticSystemInformation The new value for geodeticSystemInformation
   * @param mapBasedVersion The new value for mapBasedVersion
   * @param size The new value for size
   */
  public CommonHeader(java.lang.CharSequence dataVersion, java.lang.Long commandType, java.lang.Long destinationInformation, java.lang.Long formatInformation, java.lang.Long dcuOrDcuMeuClassification, java.lang.Long dcu, java.lang.Long meu, java.lang.Long communicationModeFlag, java.lang.Long electricPfInformation, java.lang.Long geodeticSystemInformation, java.lang.Long mapBasedVersion, java.lang.Long size) {
    this.dataVersion = dataVersion;
    this.commandType = commandType;
    this.destinationInformation = destinationInformation;
    this.formatInformation = formatInformation;
    this.dcuOrDcuMeuClassification = dcuOrDcuMeuClassification;
    this.dcu = dcu;
    this.meu = meu;
    this.communicationModeFlag = communicationModeFlag;
    this.electricPfInformation = electricPfInformation;
    this.geodeticSystemInformation = geodeticSystemInformation;
    this.mapBasedVersion = mapBasedVersion;
    this.size = size;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return dataVersion;
    case 1: return commandType;
    case 2: return destinationInformation;
    case 3: return formatInformation;
    case 4: return dcuOrDcuMeuClassification;
    case 5: return dcu;
    case 6: return meu;
    case 7: return communicationModeFlag;
    case 8: return electricPfInformation;
    case 9: return geodeticSystemInformation;
    case 10: return mapBasedVersion;
    case 11: return size;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: dataVersion = (java.lang.CharSequence)value$; break;
    case 1: commandType = (java.lang.Long)value$; break;
    case 2: destinationInformation = (java.lang.Long)value$; break;
    case 3: formatInformation = (java.lang.Long)value$; break;
    case 4: dcuOrDcuMeuClassification = (java.lang.Long)value$; break;
    case 5: dcu = (java.lang.Long)value$; break;
    case 6: meu = (java.lang.Long)value$; break;
    case 7: communicationModeFlag = (java.lang.Long)value$; break;
    case 8: electricPfInformation = (java.lang.Long)value$; break;
    case 9: geodeticSystemInformation = (java.lang.Long)value$; break;
    case 10: mapBasedVersion = (java.lang.Long)value$; break;
    case 11: size = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'dataVersion' field.
   * @return The value of the 'dataVersion' field.
   */
  public java.lang.CharSequence getDataVersion() {
    return dataVersion;
  }

  /**
   * Sets the value of the 'dataVersion' field.
   * @param value the value to set.
   */
  public void setDataVersion(java.lang.CharSequence value) {
    this.dataVersion = value;
  }

  /**
   * Gets the value of the 'commandType' field.
   * @return The value of the 'commandType' field.
   */
  public java.lang.Long getCommandType() {
    return commandType;
  }

  /**
   * Sets the value of the 'commandType' field.
   * @param value the value to set.
   */
  public void setCommandType(java.lang.Long value) {
    this.commandType = value;
  }

  /**
   * Gets the value of the 'destinationInformation' field.
   * @return The value of the 'destinationInformation' field.
   */
  public java.lang.Long getDestinationInformation() {
    return destinationInformation;
  }

  /**
   * Sets the value of the 'destinationInformation' field.
   * @param value the value to set.
   */
  public void setDestinationInformation(java.lang.Long value) {
    this.destinationInformation = value;
  }

  /**
   * Gets the value of the 'formatInformation' field.
   * @return The value of the 'formatInformation' field.
   */
  public java.lang.Long getFormatInformation() {
    return formatInformation;
  }

  /**
   * Sets the value of the 'formatInformation' field.
   * @param value the value to set.
   */
  public void setFormatInformation(java.lang.Long value) {
    this.formatInformation = value;
  }

  /**
   * Gets the value of the 'dcuOrDcuMeuClassification' field.
   * @return The value of the 'dcuOrDcuMeuClassification' field.
   */
  public java.lang.Long getDcuOrDcuMeuClassification() {
    return dcuOrDcuMeuClassification;
  }

  /**
   * Sets the value of the 'dcuOrDcuMeuClassification' field.
   * @param value the value to set.
   */
  public void setDcuOrDcuMeuClassification(java.lang.Long value) {
    this.dcuOrDcuMeuClassification = value;
  }

  /**
   * Gets the value of the 'dcu' field.
   * @return The value of the 'dcu' field.
   */
  public java.lang.Long getDcu() {
    return dcu;
  }

  /**
   * Sets the value of the 'dcu' field.
   * @param value the value to set.
   */
  public void setDcu(java.lang.Long value) {
    this.dcu = value;
  }

  /**
   * Gets the value of the 'meu' field.
   * @return The value of the 'meu' field.
   */
  public java.lang.Long getMeu() {
    return meu;
  }

  /**
   * Sets the value of the 'meu' field.
   * @param value the value to set.
   */
  public void setMeu(java.lang.Long value) {
    this.meu = value;
  }

  /**
   * Gets the value of the 'communicationModeFlag' field.
   * @return The value of the 'communicationModeFlag' field.
   */
  public java.lang.Long getCommunicationModeFlag() {
    return communicationModeFlag;
  }

  /**
   * Sets the value of the 'communicationModeFlag' field.
   * @param value the value to set.
   */
  public void setCommunicationModeFlag(java.lang.Long value) {
    this.communicationModeFlag = value;
  }

  /**
   * Gets the value of the 'electricPfInformation' field.
   * @return The value of the 'electricPfInformation' field.
   */
  public java.lang.Long getElectricPfInformation() {
    return electricPfInformation;
  }

  /**
   * Sets the value of the 'electricPfInformation' field.
   * @param value the value to set.
   */
  public void setElectricPfInformation(java.lang.Long value) {
    this.electricPfInformation = value;
  }

  /**
   * Gets the value of the 'geodeticSystemInformation' field.
   * @return The value of the 'geodeticSystemInformation' field.
   */
  public java.lang.Long getGeodeticSystemInformation() {
    return geodeticSystemInformation;
  }

  /**
   * Sets the value of the 'geodeticSystemInformation' field.
   * @param value the value to set.
   */
  public void setGeodeticSystemInformation(java.lang.Long value) {
    this.geodeticSystemInformation = value;
  }

  /**
   * Gets the value of the 'mapBasedVersion' field.
   * @return The value of the 'mapBasedVersion' field.
   */
  public java.lang.Long getMapBasedVersion() {
    return mapBasedVersion;
  }

  /**
   * Sets the value of the 'mapBasedVersion' field.
   * @param value the value to set.
   */
  public void setMapBasedVersion(java.lang.Long value) {
    this.mapBasedVersion = value;
  }

  /**
   * Gets the value of the 'size' field.
   * @return The value of the 'size' field.
   */
  public java.lang.Long getSize() {
    return size;
  }

  /**
   * Sets the value of the 'size' field.
   * @param value the value to set.
   */
  public void setSize(java.lang.Long value) {
    this.size = value;
  }

  /**
   * Creates a new CommonHeader RecordBuilder.
   * @return A new CommonHeader RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder newBuilder() {
    return new zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder();
  }

  /**
   * Creates a new CommonHeader RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CommonHeader RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder newBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder other) {
    return new zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder(other);
  }

  /**
   * Creates a new CommonHeader RecordBuilder by copying an existing CommonHeader instance.
   * @param other The existing instance to copy.
   * @return A new CommonHeader RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder newBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader other) {
    return new zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder(other);
  }

  /**
   * RecordBuilder for CommonHeader instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CommonHeader>
    implements org.apache.avro.data.RecordBuilder<CommonHeader> {

    private java.lang.CharSequence dataVersion;
    private java.lang.Long commandType;
    private java.lang.Long destinationInformation;
    private java.lang.Long formatInformation;
    private java.lang.Long dcuOrDcuMeuClassification;
    private java.lang.Long dcu;
    private java.lang.Long meu;
    private java.lang.Long communicationModeFlag;
    private java.lang.Long electricPfInformation;
    private java.lang.Long geodeticSystemInformation;
    private java.lang.Long mapBasedVersion;
    private java.lang.Long size;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.dataVersion)) {
        this.dataVersion = data().deepCopy(fields()[0].schema(), other.dataVersion);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.commandType)) {
        this.commandType = data().deepCopy(fields()[1].schema(), other.commandType);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.destinationInformation)) {
        this.destinationInformation = data().deepCopy(fields()[2].schema(), other.destinationInformation);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.formatInformation)) {
        this.formatInformation = data().deepCopy(fields()[3].schema(), other.formatInformation);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.dcuOrDcuMeuClassification)) {
        this.dcuOrDcuMeuClassification = data().deepCopy(fields()[4].schema(), other.dcuOrDcuMeuClassification);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.dcu)) {
        this.dcu = data().deepCopy(fields()[5].schema(), other.dcu);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.meu)) {
        this.meu = data().deepCopy(fields()[6].schema(), other.meu);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.communicationModeFlag)) {
        this.communicationModeFlag = data().deepCopy(fields()[7].schema(), other.communicationModeFlag);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.electricPfInformation)) {
        this.electricPfInformation = data().deepCopy(fields()[8].schema(), other.electricPfInformation);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.geodeticSystemInformation)) {
        this.geodeticSystemInformation = data().deepCopy(fields()[9].schema(), other.geodeticSystemInformation);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.mapBasedVersion)) {
        this.mapBasedVersion = data().deepCopy(fields()[10].schema(), other.mapBasedVersion);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.size)) {
        this.size = data().deepCopy(fields()[11].schema(), other.size);
        fieldSetFlags()[11] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing CommonHeader instance
     * @param other The existing instance to copy.
     */
    private Builder(zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.dataVersion)) {
        this.dataVersion = data().deepCopy(fields()[0].schema(), other.dataVersion);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.commandType)) {
        this.commandType = data().deepCopy(fields()[1].schema(), other.commandType);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.destinationInformation)) {
        this.destinationInformation = data().deepCopy(fields()[2].schema(), other.destinationInformation);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.formatInformation)) {
        this.formatInformation = data().deepCopy(fields()[3].schema(), other.formatInformation);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.dcuOrDcuMeuClassification)) {
        this.dcuOrDcuMeuClassification = data().deepCopy(fields()[4].schema(), other.dcuOrDcuMeuClassification);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.dcu)) {
        this.dcu = data().deepCopy(fields()[5].schema(), other.dcu);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.meu)) {
        this.meu = data().deepCopy(fields()[6].schema(), other.meu);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.communicationModeFlag)) {
        this.communicationModeFlag = data().deepCopy(fields()[7].schema(), other.communicationModeFlag);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.electricPfInformation)) {
        this.electricPfInformation = data().deepCopy(fields()[8].schema(), other.electricPfInformation);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.geodeticSystemInformation)) {
        this.geodeticSystemInformation = data().deepCopy(fields()[9].schema(), other.geodeticSystemInformation);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.mapBasedVersion)) {
        this.mapBasedVersion = data().deepCopy(fields()[10].schema(), other.mapBasedVersion);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.size)) {
        this.size = data().deepCopy(fields()[11].schema(), other.size);
        fieldSetFlags()[11] = true;
      }
    }

    /**
      * Gets the value of the 'dataVersion' field.
      * @return The value.
      */
    public java.lang.CharSequence getDataVersion() {
      return dataVersion;
    }

    /**
      * Sets the value of the 'dataVersion' field.
      * @param value The value of 'dataVersion'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setDataVersion(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.dataVersion = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'dataVersion' field has been set.
      * @return True if the 'dataVersion' field has been set, false otherwise.
      */
    public boolean hasDataVersion() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'dataVersion' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearDataVersion() {
      dataVersion = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'commandType' field.
      * @return The value.
      */
    public java.lang.Long getCommandType() {
      return commandType;
    }

    /**
      * Sets the value of the 'commandType' field.
      * @param value The value of 'commandType'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setCommandType(java.lang.Long value) {
      validate(fields()[1], value);
      this.commandType = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'commandType' field has been set.
      * @return True if the 'commandType' field has been set, false otherwise.
      */
    public boolean hasCommandType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'commandType' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearCommandType() {
      commandType = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'destinationInformation' field.
      * @return The value.
      */
    public java.lang.Long getDestinationInformation() {
      return destinationInformation;
    }

    /**
      * Sets the value of the 'destinationInformation' field.
      * @param value The value of 'destinationInformation'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setDestinationInformation(java.lang.Long value) {
      validate(fields()[2], value);
      this.destinationInformation = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'destinationInformation' field has been set.
      * @return True if the 'destinationInformation' field has been set, false otherwise.
      */
    public boolean hasDestinationInformation() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'destinationInformation' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearDestinationInformation() {
      destinationInformation = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'formatInformation' field.
      * @return The value.
      */
    public java.lang.Long getFormatInformation() {
      return formatInformation;
    }

    /**
      * Sets the value of the 'formatInformation' field.
      * @param value The value of 'formatInformation'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setFormatInformation(java.lang.Long value) {
      validate(fields()[3], value);
      this.formatInformation = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'formatInformation' field has been set.
      * @return True if the 'formatInformation' field has been set, false otherwise.
      */
    public boolean hasFormatInformation() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'formatInformation' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearFormatInformation() {
      formatInformation = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'dcuOrDcuMeuClassification' field.
      * @return The value.
      */
    public java.lang.Long getDcuOrDcuMeuClassification() {
      return dcuOrDcuMeuClassification;
    }

    /**
      * Sets the value of the 'dcuOrDcuMeuClassification' field.
      * @param value The value of 'dcuOrDcuMeuClassification'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setDcuOrDcuMeuClassification(java.lang.Long value) {
      validate(fields()[4], value);
      this.dcuOrDcuMeuClassification = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'dcuOrDcuMeuClassification' field has been set.
      * @return True if the 'dcuOrDcuMeuClassification' field has been set, false otherwise.
      */
    public boolean hasDcuOrDcuMeuClassification() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'dcuOrDcuMeuClassification' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearDcuOrDcuMeuClassification() {
      dcuOrDcuMeuClassification = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'dcu' field.
      * @return The value.
      */
    public java.lang.Long getDcu() {
      return dcu;
    }

    /**
      * Sets the value of the 'dcu' field.
      * @param value The value of 'dcu'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setDcu(java.lang.Long value) {
      validate(fields()[5], value);
      this.dcu = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'dcu' field has been set.
      * @return True if the 'dcu' field has been set, false otherwise.
      */
    public boolean hasDcu() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'dcu' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearDcu() {
      dcu = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'meu' field.
      * @return The value.
      */
    public java.lang.Long getMeu() {
      return meu;
    }

    /**
      * Sets the value of the 'meu' field.
      * @param value The value of 'meu'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setMeu(java.lang.Long value) {
      validate(fields()[6], value);
      this.meu = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'meu' field has been set.
      * @return True if the 'meu' field has been set, false otherwise.
      */
    public boolean hasMeu() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'meu' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearMeu() {
      meu = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'communicationModeFlag' field.
      * @return The value.
      */
    public java.lang.Long getCommunicationModeFlag() {
      return communicationModeFlag;
    }

    /**
      * Sets the value of the 'communicationModeFlag' field.
      * @param value The value of 'communicationModeFlag'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setCommunicationModeFlag(java.lang.Long value) {
      validate(fields()[7], value);
      this.communicationModeFlag = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'communicationModeFlag' field has been set.
      * @return True if the 'communicationModeFlag' field has been set, false otherwise.
      */
    public boolean hasCommunicationModeFlag() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'communicationModeFlag' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearCommunicationModeFlag() {
      communicationModeFlag = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'electricPfInformation' field.
      * @return The value.
      */
    public java.lang.Long getElectricPfInformation() {
      return electricPfInformation;
    }

    /**
      * Sets the value of the 'electricPfInformation' field.
      * @param value The value of 'electricPfInformation'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setElectricPfInformation(java.lang.Long value) {
      validate(fields()[8], value);
      this.electricPfInformation = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'electricPfInformation' field has been set.
      * @return True if the 'electricPfInformation' field has been set, false otherwise.
      */
    public boolean hasElectricPfInformation() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'electricPfInformation' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearElectricPfInformation() {
      electricPfInformation = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'geodeticSystemInformation' field.
      * @return The value.
      */
    public java.lang.Long getGeodeticSystemInformation() {
      return geodeticSystemInformation;
    }

    /**
      * Sets the value of the 'geodeticSystemInformation' field.
      * @param value The value of 'geodeticSystemInformation'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setGeodeticSystemInformation(java.lang.Long value) {
      validate(fields()[9], value);
      this.geodeticSystemInformation = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'geodeticSystemInformation' field has been set.
      * @return True if the 'geodeticSystemInformation' field has been set, false otherwise.
      */
    public boolean hasGeodeticSystemInformation() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'geodeticSystemInformation' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearGeodeticSystemInformation() {
      geodeticSystemInformation = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the 'mapBasedVersion' field.
      * @return The value.
      */
    public java.lang.Long getMapBasedVersion() {
      return mapBasedVersion;
    }

    /**
      * Sets the value of the 'mapBasedVersion' field.
      * @param value The value of 'mapBasedVersion'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setMapBasedVersion(java.lang.Long value) {
      validate(fields()[10], value);
      this.mapBasedVersion = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the 'mapBasedVersion' field has been set.
      * @return True if the 'mapBasedVersion' field has been set, false otherwise.
      */
    public boolean hasMapBasedVersion() {
      return fieldSetFlags()[10];
    }


    /**
      * Clears the value of the 'mapBasedVersion' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearMapBasedVersion() {
      mapBasedVersion = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    /**
      * Gets the value of the 'size' field.
      * @return The value.
      */
    public java.lang.Long getSize() {
      return size;
    }

    /**
      * Sets the value of the 'size' field.
      * @param value The value of 'size'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder setSize(java.lang.Long value) {
      validate(fields()[11], value);
      this.size = value;
      fieldSetFlags()[11] = true;
      return this;
    }

    /**
      * Checks whether the 'size' field has been set.
      * @return True if the 'size' field has been set, false otherwise.
      */
    public boolean hasSize() {
      return fieldSetFlags()[11];
    }


    /**
      * Clears the value of the 'size' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader.Builder clearSize() {
      size = null;
      fieldSetFlags()[11] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CommonHeader build() {
      try {
        CommonHeader record = new CommonHeader();
        record.dataVersion = fieldSetFlags()[0] ? this.dataVersion : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.commandType = fieldSetFlags()[1] ? this.commandType : (java.lang.Long) defaultValue(fields()[1]);
        record.destinationInformation = fieldSetFlags()[2] ? this.destinationInformation : (java.lang.Long) defaultValue(fields()[2]);
        record.formatInformation = fieldSetFlags()[3] ? this.formatInformation : (java.lang.Long) defaultValue(fields()[3]);
        record.dcuOrDcuMeuClassification = fieldSetFlags()[4] ? this.dcuOrDcuMeuClassification : (java.lang.Long) defaultValue(fields()[4]);
        record.dcu = fieldSetFlags()[5] ? this.dcu : (java.lang.Long) defaultValue(fields()[5]);
        record.meu = fieldSetFlags()[6] ? this.meu : (java.lang.Long) defaultValue(fields()[6]);
        record.communicationModeFlag = fieldSetFlags()[7] ? this.communicationModeFlag : (java.lang.Long) defaultValue(fields()[7]);
        record.electricPfInformation = fieldSetFlags()[8] ? this.electricPfInformation : (java.lang.Long) defaultValue(fields()[8]);
        record.geodeticSystemInformation = fieldSetFlags()[9] ? this.geodeticSystemInformation : (java.lang.Long) defaultValue(fields()[9]);
        record.mapBasedVersion = fieldSetFlags()[10] ? this.mapBasedVersion : (java.lang.Long) defaultValue(fields()[10]);
        record.size = fieldSetFlags()[11] ? this.size : (java.lang.Long) defaultValue(fields()[11]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CommonHeader>
    WRITER$ = (org.apache.avro.io.DatumWriter<CommonHeader>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CommonHeader>
    READER$ = (org.apache.avro.io.DatumReader<CommonHeader>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
