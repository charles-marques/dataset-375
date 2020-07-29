// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package hoptoad;

import org.apache.log4j.*;
import org.apache.log4j.spi.*;

public class HoptoadAppender extends AppenderSkeleton {
	private final HoptoadNotifier hoptoadNotifier = new HoptoadNotifier();

	private String apiKey;

	private String env;

	private boolean enabled;

	private Backtrace backtrace = new QuietRubyBacktrace();

    private String endpoint = "airbrake.io/notifier_api/v2/notices";

    private boolean secure = false;

	public HoptoadAppender() {
		setThreshold(Level.ERROR);
	}

	public HoptoadAppender(final String apiKey) {
		setApi_key(apiKey);
	}

	public HoptoadAppender(final String apiKey, final Backtrace backtrace) {
		setApi_key(apiKey);
		setBacktrace(backtrace);
	}

	@Override
	protected void append(final LoggingEvent loggingEvent) {
		if (!enabled) return;

		if (thereIsThrowableIn(loggingEvent)) {
			notifyThrowableIn(loggingEvent);
		}
	}

	@Override
	public void close() {}

	public HoptoadNotice newNoticeFor(final Throwable throwable) {
		return new HoptoadNoticeBuilderUsingFilteredSystemProperties(apiKey, backtrace, throwable, env).newNotice();
	}

	private int notifyThrowableIn(final LoggingEvent loggingEvent) {
		return hoptoadNotifier.notify(newNoticeFor(throwable(loggingEvent)), endpoint, secure);
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	public void setApi_key(final String apiKey) {
		this.apiKey = apiKey;
	}

	public void setBacktrace(final Backtrace backtrace) {
		this.backtrace = backtrace;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public void setEnv(final String env) {
		this.env = env;
	}

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

	private boolean thereIsThrowableIn(final LoggingEvent loggingEvent) {
		return loggingEvent.getThrowableInformation() != null;
	}

	private Throwable throwable(final LoggingEvent loggingEvent) {
		return loggingEvent.getThrowableInformation().getThrowable();
	}
}
