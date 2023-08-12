package dev.rodrigomuller.stocksanalyzer.stock.queue;

import dev.rodrigomuller.stocksanalyzer.stock.dto.thirdparty.marketstack.DailyTradingMessageDTO;
import dev.rodrigomuller.stocksanalyzer.stock.service.TradingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TradingConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradingConsumer.class);

    private final TradingService tradingService;

    public TradingConsumer(TradingService tradingService) {
        this.tradingService = tradingService;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(DailyTradingMessageDTO message) {
        LOGGER.info(String.format("Received message -> %s", message.toString()));

        tradingService.syncTrading(message);
    }
}
