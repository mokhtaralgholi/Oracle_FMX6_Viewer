package info.phosco.forms.translate.bytes;

public enum FileStructureType {

	TEXT(0x1), ATTRIBUTES(0x2), Unknown(0x3);

	private final int type;

	private FileStructureType(int type) {
		this.type = type;
	}

	public int id() {
		return this.type;
	}

	public static FileStructureType lookup(int type) {
		for (FileStructureType t : FileStructureType.values()) {
			if (t.type == type) {
				return t;
			}
		}
		return FileStructureType.ATTRIBUTES;
	}
}