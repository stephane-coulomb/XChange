package com.xeiam.xchange.btcchina.service.streaming;

import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.streaming.ExchangeStreamingConfiguration;

public class BTCChinaStreamingConfiguration implements ExchangeStreamingConfiguration {

  private final CurrencyPair[] marketDataCurrencyPairs;
  private final CurrencyPair[] orderFeedCurrencyPairs;

  public BTCChinaStreamingConfiguration() {

    this(true, true, CurrencyPair.BTC_CNY, CurrencyPair.LTC_CNY, CurrencyPair.LTC_BTC);
  }

  /**
   * @param subscribeMarketData Subscribe with 'subscribe' method and listen on "ticker" and "trade" method to received and process market data including ticker data and trade data.
   * @param subscribeOrderFeed Subscribe with 'private' method and listen on "order" method to receive and process your own order data. Whenever the status of your own orders change, the server will
   *          push corresponding orders to your client side.
   *          This requires authentication with your access key and secret key, which is used the same way as our trade API.
   * @param currencyPairs could be one or more of {@link CurrencyPair#BTC_CNY}, {@link CurrencyPair#LTC_CNY}, {@link CurrencyPair#LTC_BTC}.
   */
  public BTCChinaStreamingConfiguration(boolean subscribeMarktData, boolean subscribeOrderFeed, CurrencyPair... currencyPairs) {

    this(subscribeMarktData ? currencyPairs : null, subscribeOrderFeed ? currencyPairs : null);
  }

  public BTCChinaStreamingConfiguration(CurrencyPair[] marketDataCurrencyPairs, CurrencyPair[] orderFeedCurrencyPairs) {

    this.marketDataCurrencyPairs = marketDataCurrencyPairs == null ? new CurrencyPair[0] : marketDataCurrencyPairs;
    this.orderFeedCurrencyPairs = orderFeedCurrencyPairs == null ? new CurrencyPair[0] : orderFeedCurrencyPairs;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getMaxReconnectAttempts() {

    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getReconnectWaitTimeInMs() {

    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTimeoutInMs() {

    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEncryptedChannel() {

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean keepAlive() {

    return false;
  }

  @Deprecated
  public boolean isSubscribeMarketData() {

    return marketDataCurrencyPairs.length > 0;
  }

  @Deprecated
  public boolean isSubscribeOrderFeed() {

    return orderFeedCurrencyPairs.length > 0;
  }

  @Deprecated
  public CurrencyPair[] getCurrencyPairs() {

    return marketDataCurrencyPairs;
  }

  public CurrencyPair[] getMarketDataCurrencyPairs() {

    return marketDataCurrencyPairs;
  }

  public CurrencyPair[] getOrderFeedCurrencyPairs() {

    return orderFeedCurrencyPairs;
  }

}
