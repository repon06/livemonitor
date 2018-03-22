package winline.livemonitor.models;

/**
 * Виды спорта и типы иконок для вроверки
 * 
 * @author repon
 *
 */
public class Sport {
	private static final String[] all = { "Все", "" };
	private static final String[] video = { "Трансляции", "icon-tv" };
	private static final String[] footbal = { "Футбол", "icon-1444680299_football_px" };
	private static final String[] basketball = { "Баскетбол", "icon-1444677386_basketball_px" };
	private static final String[] baseball = { "Бейсбол", "icon-1444681211_Baseball_Ball" };
	private static final String[] hockey = { "Хоккей", "icon-1444680638_ice_hockey" };
	private static final String[] tennis = { "Теннис", "icon-1444680487_tennis_px" };
	private static final String[] volleyball = { "Волейбол", "icon-1444677406_volleyball_px" };

	public static String getIcon(String name) {
		switch (name.toLowerCase()) {
		case "трансляции":
			return video_icon();
		case "футбол":
			return footbal_icon();
		case "баскетбол":
			return basketball_icon();
		case "бейсбол":
			return baseball_icon();
		case "хоккей":
			return hockey_icon();
		case "теннис":
			return tennis_icon();
		case "волейбол":
			return volleyball_icon();
		default:
			return null;
		}

	}

	public static String all() {
		return Sport.all[0];
	}

	public static String video() {
		return Sport.video[0];
	}

	public static String video_icon() {
		return Sport.video[1];
	}

	public static String footbal() {
		return Sport.footbal[0];
	}

	public static String footbal_icon() {
		return Sport.footbal[1];
	}

	public static String basketball() {
		return Sport.basketball[0];
	}

	public static String basketball_icon() {
		return Sport.basketball[1];
	}

	public static String baseball() {
		return Sport.baseball[0];
	}

	public static String baseball_icon() {
		return Sport.baseball[1];
	}

	public static String hockey() {
		return Sport.hockey[0];
	}

	public static String hockey_icon() {
		return Sport.hockey[1];
	}

	public static String tennis() {
		return Sport.tennis[0];
	}

	public static String tennis_icon() {
		return Sport.tennis[1];
	}

	public static String volleyball() {
		return Sport.volleyball[0];
	}

	public static String volleyball_icon() {
		return Sport.volleyball[1];
	}

}
