package me.loki2302.app.commands;

import me.loki2302.app.App;

public interface ApplicationCommand<TResult> {
    TResult run(App app);
}
