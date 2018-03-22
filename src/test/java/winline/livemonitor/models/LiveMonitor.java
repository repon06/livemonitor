package winline.livemonitor.models;

import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;

public class LiveMonitor {

	private String id;
	// private SelenideElement locatorId;
	private SelenideElement locatorDropzone;
	private String CssSelectorDropzone;

	private String name;
	// private SelenideElement locatorName;
	private SelenideElement locatorNameP;
	private SelenideElement locatorNameInput;

	private String text;
	// private SelenideElement locatorText;
	private String icon;

	private SelenideElement locatoradv;
	private SelenideElement locatorRemove;
	private SelenideElement locatorPlaylist;
	private SelenideElement locatorReload;

	/*
	 * public LiveMonitor(String id, SelenideElement locatorDropzone, String
	 * name, SelenideElement locatorNameP, SelenideElement locatorNameInput,
	 * String text, String icon, SelenideElement locatoradv, SelenideElement
	 * locatorRemove, SelenideElement locatorPlaylist, SelenideElement
	 * locatorReload)
	 */
	public LiveMonitor(String id, String name, String text, String icon) {

		setId(id);
		setCssSelectorDropzone(id);
		setText(text);
		setName(name);
		setIcon(icon);

		// this.setLocatorId(locatorId);
		// setLocatorDropzone(locatorDropzone);
		// this.setLocatorName(locatorName);
		// setLocatorNameP(locatorNameP);
		// setLocatorNameInput(locatorNameInput);
		// this.setLocatorText(locatorText);
		// setLocatoradv(locatoradv);
		// setLocatorRemove(locatorRemove);
		// setLocatorPlaylist(locatorPlaylist);
		// setLocatorReload(locatorReload);
	}

	public void updateMonitor(String text, String icon) {
		setText(text);
		setIcon(icon);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public SelenideElement getLocatorDropzone() { return locatorDropzone; }
	 * 
	 * public void setLocatorDropzone(SelenideElement locatorDropzone) {
	 * this.locatorDropzone = locatorDropzone; }
	 * 
	 * public SelenideElement getLocatorNameP() { return locatorNameP; }
	 * 
	 * public void setLocatorNameP(SelenideElement locatorNameP) {
	 * this.locatorNameP = locatorNameP; }
	 * 
	 * public SelenideElement getLocatorNameInput() { return locatorNameInput; }
	 * 
	 * public void setLocatorNameInput(SelenideElement locatorNameInput) {
	 * this.locatorNameInput = locatorNameInput; }
	 * 
	 * public SelenideElement getLocatoradv() { return locatoradv; }
	 * 
	 * public void setLocatoradv(SelenideElement locatoradv) { this.locatoradv =
	 * locatoradv; }
	 * 
	 * public SelenideElement getLocatorRemove() { return locatorRemove; }
	 * 
	 * public void setLocatorRemove(SelenideElement locatorRemove) {
	 * this.locatorRemove = locatorRemove; }
	 * 
	 * public SelenideElement getLocatorPlaylist() { return locatorPlaylist; }
	 * 
	 * public void setLocatorPlaylist(SelenideElement locatorPlaylist) {
	 * this.locatorPlaylist = locatorPlaylist; }
	 * 
	 * public SelenideElement getLocatorReload() { return locatorReload; }
	 * 
	 * public void setLocatorReload(SelenideElement locatorReload) {
	 * this.locatorReload = locatorReload; }
	 * 
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCssSelectorDropzone() {
		return CssSelectorDropzone.replace("'", "\"");
	}

	public void setCssSelectorDropzone(String id) {
		CssSelectorDropzone = "div[id=\"" + id + "\"].dropzone";
		// [id='5844'].dropzone
	}

}
