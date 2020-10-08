package primitive;

public enum AccessFlags
{
    None ((byte) 0x0000),
    AccPublic ((byte)0x0001),
    AccPrivate ((byte)0x0002),
    AccProtected ((byte)0x0004),
    AccStatic ((byte)0x0008),
    AccFinal ((byte)0x0010),
    AccSynchronized ((byte)0x0020),
    AccVolatile ((byte)0x0040),
    AccVarargs ((byte)0x0080),
    AccInterface ((byte)0x0200),
    AccAbstract ((byte)0x0400),
    AccStrict ((byte)0x0800),
    AccEnum ((byte)0x4000);

    private byte value;

    AccessFlags(byte i) {
        value = i;
    }

    public byte getValue() { return value; }
}
