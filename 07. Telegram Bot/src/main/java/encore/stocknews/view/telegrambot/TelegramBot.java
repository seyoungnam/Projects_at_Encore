package encore.stocknews.view.telegrambot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot {
	public static void main(String[] args) {
		TelegramBotsApi api = new TelegramBotsApi();
		try {
			ApiContextInitializer.init();
			api.registerBot(new BotHandler());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
