package info.phosco.forms.translate.element.canvas.graphic;

public enum RectangleBevel {

	RAISED(0x18), LOWERED(0x20), NONE(0x0), INSET(0x8), OUTSET(0x10), PLAIN(0x0);

	private final int bevel;

	private RectangleBevel(int bevel) {
		this.bevel = bevel;
	}

	public static RectangleBevel lookup(int bevel) {
		for (RectangleBevel t : RectangleBevel.values()) {
			if (t.bevel == bevel) {
				return t;
			}
		}
	//	throw new IllegalArgumentException("Unknown RectangleBevel " + bevel);
return RectangleBevel.NONE;
	}
}
