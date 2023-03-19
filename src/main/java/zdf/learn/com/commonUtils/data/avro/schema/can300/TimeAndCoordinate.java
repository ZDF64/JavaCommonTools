/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class TimeAndCoordinate extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5967507780228072035L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TimeAndCoordinate\",\"namespace\":\"src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300\",\"fields\":[{\"name\":\"gps\",\"type\":{\"type\":\"record\",\"name\":\"GPS\",\"fields\":[{\"name\":\"gpsDate\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"point\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Point\",\"fields\":[{\"name\":\"latitude\",\"type\":[\"null\",\"double\"],\"default\":null},{\"name\":\"longitude\",\"type\":[\"null\",\"double\"],\"default\":null}]}]},{\"name\":\"pdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"hdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"vdop\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"measureCount\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}},{\"name\":\"mm\",\"type\":{\"type\":\"record\",\"name\":\"MM\",\"fields\":[{\"name\":\"point\",\"type\":[\"null\",\"Point\"],\"default\":null},{\"name\":\"rticLinkId\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}}],\"default\":null}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TimeAndCoordinate> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TimeAndCoordinate> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<TimeAndCoordinate> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<TimeAndCoordinate> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<TimeAndCoordinate> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this TimeAndCoordinate to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a TimeAndCoordinate from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a TimeAndCoordinate instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static TimeAndCoordinate fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS gps;
  private src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM mm;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TimeAndCoordinate() {}

  /**
   * All-args constructor.
   * @param gps The new value for gps
   * @param mm The new value for mm
   */
  public TimeAndCoordinate(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS gps, src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM mm) {
    this.gps = gps;
    this.mm = mm;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return gps;
    case 1: return mm;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: gps = (src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS)value$; break;
    case 1: mm = (src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'gps' field.
   * @return The value of the 'gps' field.
   */
  public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS getGps() {
    return gps;
  }


  /**
   * Sets the value of the 'gps' field.
   * @param value the value to set.
   */
  public void setGps(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS value) {
    this.gps = value;
  }

  /**
   * Gets the value of the 'mm' field.
   * @return The value of the 'mm' field.
   */
  public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM getMm() {
    return mm;
  }


  /**
   * Sets the value of the 'mm' field.
   * @param value the value to set.
   */
  public void setMm(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM value) {
    this.mm = value;
  }

  /**
   * Creates a new TimeAndCoordinate RecordBuilder.
   * @return A new TimeAndCoordinate RecordBuilder
   */
  public static src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder newBuilder() {
    return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder();
  }

  /**
   * Creates a new TimeAndCoordinate RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TimeAndCoordinate RecordBuilder
   */
  public static src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder newBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder other) {
    if (other == null) {
      return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder();
    } else {
      return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder(other);
    }
  }

  /**
   * Creates a new TimeAndCoordinate RecordBuilder by copying an existing TimeAndCoordinate instance.
   * @param other The existing instance to copy.
   * @return A new TimeAndCoordinate RecordBuilder
   */
  public static src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder newBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate other) {
    if (other == null) {
      return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder();
    } else {
      return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder(other);
    }
  }

  /**
   * RecordBuilder for TimeAndCoordinate instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TimeAndCoordinate>
    implements org.apache.avro.data.RecordBuilder<TimeAndCoordinate> {

    private src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS gps;
    private src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS.Builder gpsBuilder;
    private src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM mm;
    private src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM.Builder mmBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.gps)) {
        this.gps = data().deepCopy(fields()[0].schema(), other.gps);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (other.hasGpsBuilder()) {
        this.gpsBuilder = src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS.newBuilder(other.getGpsBuilder());
      }
      if (isValidValue(fields()[1], other.mm)) {
        this.mm = data().deepCopy(fields()[1].schema(), other.mm);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (other.hasMmBuilder()) {
        this.mmBuilder = src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM.newBuilder(other.getMmBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing TimeAndCoordinate instance
     * @param other The existing instance to copy.
     */
    private Builder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.gps)) {
        this.gps = data().deepCopy(fields()[0].schema(), other.gps);
        fieldSetFlags()[0] = true;
      }
      this.gpsBuilder = null;
      if (isValidValue(fields()[1], other.mm)) {
        this.mm = data().deepCopy(fields()[1].schema(), other.mm);
        fieldSetFlags()[1] = true;
      }
      this.mmBuilder = null;
    }

    /**
      * Gets the value of the 'gps' field.
      * @return The value.
      */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS getGps() {
      return gps;
    }


    /**
      * Sets the value of the 'gps' field.
      * @param value The value of 'gps'.
      * @return This builder.
      */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder setGps(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS value) {
      validate(fields()[0], value);
      this.gpsBuilder = null;
      this.gps = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'gps' field has been set.
      * @return True if the 'gps' field has been set, false otherwise.
      */
    public boolean hasGps() {
      return fieldSetFlags()[0];
    }

    /**
     * Gets the Builder instance for the 'gps' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS.Builder getGpsBuilder() {
      if (gpsBuilder == null) {
        if (hasGps()) {
          setGpsBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS.newBuilder(gps));
        } else {
          setGpsBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS.newBuilder());
        }
      }
      return gpsBuilder;
    }

    /**
     * Sets the Builder instance for the 'gps' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder setGpsBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS.Builder value) {
      clearGps();
      gpsBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'gps' field has an active Builder instance
     * @return True if the 'gps' field has an active Builder instance
     */
    public boolean hasGpsBuilder() {
      return gpsBuilder != null;
    }

    /**
      * Clears the value of the 'gps' field.
      * @return This builder.
      */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder clearGps() {
      gps = null;
      gpsBuilder = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'mm' field.
      * @return The value.
      */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM getMm() {
      return mm;
    }


    /**
      * Sets the value of the 'mm' field.
      * @param value The value of 'mm'.
      * @return This builder.
      */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder setMm(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM value) {
      validate(fields()[1], value);
      this.mmBuilder = null;
      this.mm = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'mm' field has been set.
      * @return True if the 'mm' field has been set, false otherwise.
      */
    public boolean hasMm() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'mm' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM.Builder getMmBuilder() {
      if (mmBuilder == null) {
        if (hasMm()) {
          setMmBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM.newBuilder(mm));
        } else {
          setMmBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM.newBuilder());
        }
      }
      return mmBuilder;
    }

    /**
     * Sets the Builder instance for the 'mm' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder setMmBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM.Builder value) {
      clearMm();
      mmBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'mm' field has an active Builder instance
     * @return True if the 'mm' field has an active Builder instance
     */
    public boolean hasMmBuilder() {
      return mmBuilder != null;
    }

    /**
      * Clears the value of the 'mm' field.
      * @return This builder.
      */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate.Builder clearMm() {
      mm = null;
      mmBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TimeAndCoordinate build() {
      try {
        TimeAndCoordinate record = new TimeAndCoordinate();
        if (gpsBuilder != null) {
          try {
            record.gps = this.gpsBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("gps"));
            throw e;
          }
        } else {
          record.gps = fieldSetFlags()[0] ? this.gps : (src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS) defaultValue(fields()[0]);
        }
        if (mmBuilder != null) {
          try {
            record.mm = this.mmBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("mm"));
            throw e;
          }
        } else {
          record.mm = fieldSetFlags()[1] ? this.mm : (src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM) defaultValue(fields()[1]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TimeAndCoordinate>
    WRITER$ = (org.apache.avro.io.DatumWriter<TimeAndCoordinate>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TimeAndCoordinate>
    READER$ = (org.apache.avro.io.DatumReader<TimeAndCoordinate>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    this.gps.customEncode(out);

    this.mm.customEncode(out);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (this.gps == null) {
        this.gps = new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS();
      }
      this.gps.customDecode(in);

      if (this.mm == null) {
        this.mm = new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM();
      }
      this.mm.customDecode(in);

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (this.gps == null) {
            this.gps = new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.GPS();
          }
          this.gps.customDecode(in);
          break;

        case 1:
          if (this.mm == null) {
            this.mm = new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.MM();
          }
          this.mm.customDecode(in);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










