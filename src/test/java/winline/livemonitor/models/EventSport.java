package winline.livemonitor.models;

import org.apache.log4j.Logger;


public class EventSport {
	private final static Logger LOG = Logger.getLogger(EventSport.class);

	private String id;
	private String cssDraggeble;
	private String name;
	private String icon;
	private String period;
	private String score;
	private String players;
	private boolean isTranslation;

	public EventSport(String id, String name, String icon, String period, String score, String players,
			boolean isTranslation) {
		setId(id);
		setName(name);
		setIcon(icon);
		setPeriod(period);
		setScore(score);
		setPlayers(players);
		setCssDraggable(id);
		setIsTranslation(isTranslation);

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsTranslation() {
		return isTranslation;
	}

	public void setIsTranslation(boolean state) {
		this.isTranslation = state;
	}

	public void setCssDraggable(String id) {
		this.cssDraggeble = "tbody#events tr.item[number_event=\"" + id + "\"]";
	}

	public String getCssDraggable() {
		return cssDraggeble;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

}
