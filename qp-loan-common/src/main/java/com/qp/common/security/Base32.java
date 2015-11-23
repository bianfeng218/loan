package com.qp.common.security;


/**
 * @author haiping
 *
 */
public class Base32 {
	@SuppressWarnings("unused")
	private static final String base32Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
	private static final int[] base32Lookup = { 255, 255, 26, 27, 28, 29, 30,
			31, 255, 255, 255, 255, 255, 255, 255, 255, 255, 0, 1, 2, 3, 4, 5,
			6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
			24, 25, 255, 255, 255, 255, 255, 255, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
			255, 255, 255, 255, 255 };

	public static String encode(byte[] bytes) {
		int i = 0;
		int index = 0;
		int digit = 0;

		int add = 0;
		switch (bytes.length) {
		case 1:
			add = 6;
			break;
		case 2:
			add = 4;
			break;
		case 3:
			add = 3;
			break;
		case 4:
			add = 1;
		}

		StringBuffer base32 = new StringBuffer((bytes.length + 7) * 8 / 5 + add);

		while (i < bytes.length) {
			int currByte = bytes[i] >= 0 ? bytes[i] : bytes[i] + 256;

			if (index > 3) {
				int nextByte;
				if (i + 1 < bytes.length)
					nextByte = bytes[(i + 1)] >= 0 ? bytes[(i + 1)]
							: bytes[(i + 1)] + 256;
				else {
					nextByte = 0;
				}
				digit = currByte & 255 >> index;
				index = (index + 5) % 8;
				digit <<= index;
				digit |= nextByte >> 8 - index;
				i++;
			} else {
				digit = currByte >> 8 - (index + 5) & 0x1F;
				index = (index + 5) % 8;
				if (index == 0)
					i++;
			}
			base32.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".charAt(digit));
		}

		switch (bytes.length) {
		case 1:
			base32.append("======");
			break;
		case 2:
			base32.append("====");
			break;
		case 3:
			base32.append("===");
			break;
		case 4:
			base32.append("=");
		}

		return base32.toString();
	}

	public static byte[] decode(String base32) {
		byte[] bytes = new byte[base32.length() * 5 / 8];

		int i = 0;
		int index = 0;
		for (int offset = 0; i < base32.length(); i++) {
			int lookup = base32.charAt(i) - '0';

			if ((lookup >= 0) && (lookup < base32Lookup.length)) {
				int digit = base32Lookup[lookup];

				if (digit != 255) {
					if (index <= 3) {
						index = (index + 5) % 8;
						if (index == 0) {
							int tmp85_83 = offset;
							byte[] tmp85_81 = bytes;
							tmp85_81[tmp85_83] = ((byte) (tmp85_81[tmp85_83] | digit));
							offset++;
							if (offset >= bytes.length)
								break;
						} else {
							int tmp110_108 = offset;
							byte[] tmp110_106 = bytes;
							tmp110_106[tmp110_108] = ((byte) (tmp110_106[tmp110_108] | digit << 8 - index));
						}
					} else {
						index = (index + 5) % 8;
						int tmp136_134 = offset;
						byte[] tmp136_132 = bytes;
						tmp136_132[tmp136_134] = ((byte) (tmp136_132[tmp136_134] | digit >>> index));
						offset++;

						if (offset >= bytes.length)
							break;
						int tmp163_161 = offset;
						byte[] tmp163_159 = bytes;
						tmp163_159[tmp163_161] = ((byte) (tmp163_159[tmp163_161] | digit << 8 - index));
					}
				}
			}
		}
		return bytes;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Supply a Base32-encoded argument.");
			return;
		}
		System.out.println(" Original: " + args[0]);
		byte[] decoded = decode(args[0]);
		System.out.print("      Hex: ");
		for (int i = 0; i < decoded.length; i++) {
			int b = decoded[i];
			if (b < 0)
				b += 256;
			System.out.print(Integer.toHexString(b + 256).substring(1));
		}
		System.out.println();
		System.out.println("Reencoded: " + encode(decoded));
	}
}