package org.kehd.scoreboardextra.main;

import org.kehd.scoreboardextra.settings.Settings;
import org.kehd.scoreboardextra.tablist.NewTabList;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.Arrays;
import java.util.List;

public class ScoreBoardExtraCore extends SimplePlugin {

	private static ScoreBoardExtraCore plugin;

	@Override
	protected void onPluginStart() {
		plugin = this;

		registerEvents(new NewTabList());

	}

	public static ScoreBoardExtraCore getPlugin() {
		return plugin;
	}

	@Override
	public List<Class<? extends YamlStaticConfig>> getSettings() {
		return Arrays.asList(Settings.class);
	}
}
	