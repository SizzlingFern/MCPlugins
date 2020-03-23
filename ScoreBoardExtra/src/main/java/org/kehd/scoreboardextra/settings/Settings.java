package org.kehd.scoreboardextra.settings;

import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {

	@Override
	protected int getConfigVersion() {
		return 0;
	}

	public static String Tab_List_Header;
	public static String Tab_List_Footer;

	private static void init() {
		pathPrefix("Tab_List");

		Tab_List_Header = getString("Header");
		Tab_List_Footer = getString("Footer");


	}
}
