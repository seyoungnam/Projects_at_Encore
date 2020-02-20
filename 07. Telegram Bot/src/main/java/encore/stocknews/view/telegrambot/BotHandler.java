package encore.stocknews.view.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import encore.stocknews.model.dto.Stock;
import encore.stocknews.model.dto.StockNews;
import encore.stocknews.model.util.DBUtil;
import encore.stocknews.service.StockNewsService;

public class BotHandler extends TelegramLongPollingBot {
	public static StockNewsService service = StockNewsService.getInstance();

	@Override
	public String getBotUsername() {
		return "StockNewsAlarmBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			String msg = update.getMessage().getText();
			System.out.println(msg);
			SendMessage sendMsg = new SendMessage();
			sendMsg.setChatId(update.getMessage().getChatId());
			try {
				DBUtil.runFactory();
				for (StockNews news : service.getStockNewsList(msg)) {
					execute(sendMsg.setText(news.toString()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeFactory();
			}
		}
	}

	@Override
	public String getBotToken() {
		return "875167954:AAH8j_9h9HQ7Cftilc9h99QeVWy0ouMqsfo";
	}

}