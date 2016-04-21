package clarke.sw.scraper;

public class RoundDecimal {
	public double round(double value, int places) {
		try {
			if (places < 0)
				;
		} catch (IllegalArgumentException e) {
			System.out.println("Error in round method " + e);
		}
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public double convertToDecimal(String ratio) {
		try {
			if (ratio.contains("/")) {
				String[] rat = ratio.split("/");
				double ans = Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
				return round(ans, 3);
			} else if (ratio.equalsIgnoreCase("EVS") || ratio.equalsIgnoreCase("evens")) {
				return 1;
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			System.out.println("convert to decimal error: " + e);
			return 0;
		}
	}
}
