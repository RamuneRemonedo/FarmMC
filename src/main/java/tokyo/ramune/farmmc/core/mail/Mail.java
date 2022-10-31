package tokyo.ramune.farmmc.core.mail;

import tokyo.ramune.farmmc.core.database.SQLDate;

import javax.annotation.Nonnull;
import java.util.UUID;

public interface Mail {
    @Nonnull
    SQLDate getDate();

    boolean isRead();

    @Nonnull
    UUID getSenderUniqueId();

    @Nonnull
    UUID getRecipientUniqueId();

    @Nonnull
    String getSubject();

    @Nonnull
    String getMessage();
}
