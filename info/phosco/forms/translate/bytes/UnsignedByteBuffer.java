package info.phosco.forms.translate.bytes;


public class UnsignedByteBuffer {

	private byte[] array;

	private UnsignedByteBuffer(byte[] a) {
		array = new byte[a.length];
		System.arraycopy(a, 0, array, 0, a.length);
	}

	private UnsignedByteBuffer(byte[] a, int offset, int length) {
		array = new byte[length];
		System.arraycopy(a, offset, array, 0, length);
	}

	public int getInt(int pos) {
		long l = (long) array[pos] & 0xFF;
		l += ((long) array[pos + 1] & 0xFF) << 8;
		l += ((long) array[pos + 2] & 0xFF) << 16;
		l += ((long) array[pos + 3] & 0xFF) << 24;
		return Long.valueOf(l).intValue();
	}

	public int getShort(int pos) {
    // ≈–« ﬂ«‰ «·„Ê÷⁄ Œ«—Ã «·ÕœÊœ
    if (pos < 0 || pos + 1 >= array.length) {
        return 1; // ﬁÌ„… «› —«÷Ì…
    }

    // ﬁ—«¡… «·»«Ì Ì‰ Ê ÕÊÌ·Â„« ≈·Ï ﬁÌ„… ﬁ’Ì—…
    long l = (long) array[pos] & 0xFF;
    l += ((long) array[pos + 1] & 0xFF) << 8;
    return Long.valueOf(l).intValue();
}

	public int getByte(int pos) {
    
    if (pos < 0 || pos + 1 >= array.length) {
        return 1; // ﬁÌ„… «› —«÷Ì…
    }
		return array[pos] & 0xFF;
	}

	public byte[] getBytes(int pos, int len) {
    //  Õﬁﬁ „‰ «·„Ê÷⁄ Ê«·ÿÊ·
    if (pos < 0 || pos >= array.length || len < 0 || pos + len > array.length) {
        // ≈–« ﬂ«‰ «·„Ê÷⁄ √Ê «·ÿÊ· €Ì— ’«·Õ°  ı—Ã⁄ „’›Ê›… ›«—€…
        return new byte[1];
    }

    byte[] res = new byte[len];
    System.arraycopy(array, pos, res, 0, len); // ‰”Œ «·»Ì«‰« 
    return res;
}

	public int getLength() {
		return array.length;
	}

	public static UnsignedByteBuffer wrap(byte[] array) {
		return new UnsignedByteBuffer(array);
	}

	public static UnsignedByteBuffer wrap(byte[] array, int offset, int length) {
		return new UnsignedByteBuffer(array, offset, length);
	}

	public static UnsignedByteBuffer wrap(UnsignedByteBuffer buf, int offset, int length) {
		return new UnsignedByteBuffer(buf.array, offset, length);
	}

	public byte[] readToNull(int pos) {
    // «· Õﬁﬁ „‰ √‰ «·„Ê÷⁄ „»œ∆Ì ’«·Õ
    if (pos < 0 || pos >= array.length) {
        return new byte[1]; // ≈—Ã«⁄ „’›Ê›… ›«—€… ≈–« ﬂ«‰ «·„Ê÷⁄ €Ì— ’«·Õ
    }

    int i = pos;
    // «·Õ·ﬁ«  ÷„‰ «·ÕœÊœ
    while (i < array.length && getByte(i) != 0x0) {
        i++;
    }

    // Õ”«» «·ÿÊ· »√„«‰
    int length = i - pos;
    if (length <= 0) {
        return new byte[0]; // ≈–« ﬂ«‰ «·ÿÊ· €Ì— ’«·Õ° ≈—Ã«⁄ „’›Ê›… ›«—€…
    }

    // ≈‰‘«¡ ‰”Œ… „‰ «·»Ì«‰« 
    byte[] res = new byte[length];
    System.arraycopy(array, pos, res, 0, length);
    return res;
}

	public int findHex(int offset, int... hex) {
		
		if (offset < 0 || offset >= getLength()) {
		//	throw new ArrayIndexOutOfBoundsException(offset);
          return offset;
		}
		
		for (int i = offset; i < getLength(); i++) {
			boolean found = true;
			for (int j = 0; j < hex.length; j++) {
				if (getByte(i + j) != hex[j]) {
					found = true;
					break;
				}
			}

			if (found) {
				return i;
			}
		}
		 return 0xFFFFFFFF;
	}

}
