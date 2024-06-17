package com.cbastian.apicat.resources.usecase;

import com.cbastian.apicat.resources.kernel.command.Command;

@FunctionalInterface
public interface UseCase<T extends Command, R> {
    R execute(T command);
}
