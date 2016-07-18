/*
 * Copyright (C) 2016 Luca Corbatto
 *
 * This file is part of the hsReisePlugin.
 *
 * The hsReisePlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The hsReisePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package reiseplugin;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class WTFException extends RuntimeException {
    /**
     * Creates a new WTFException.
     */
    public WTFException() {
        super("This should never have happened.");
    }

    /**
     * Creates a new WTFException with a message.
     * @param message The exception message.
     */
    public WTFException(String message) {
        super(message);
    }

    /**
     * Creates a new WTFException with a message and a cause.
     * @param message The exception message.
     * @param cause The cause.
     */
    public WTFException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new WTFException with a cause.
     * @param cause The cause.
     */
    public WTFException(Throwable cause) {
        super(cause);
    }
}
