package tokyo.ramune.farmmc.utility;

import com.google.common.util.concurrent.RateLimiter;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class FarmRateLimiter<T> {
    private final Map<T, RateLimiter> rateLimiter = new HashMap<>();
    private final double permitsPerSeconds;

    public FarmRateLimiter(double permitsPerSeconds) {
        this.permitsPerSeconds = permitsPerSeconds;
    }

    public void add(@Nonnull T type) {
        if (isContains(type))
            return;

        rateLimiter.put(type, RateLimiter.create(permitsPerSeconds));
    }

    public void remove(@Nonnull T type) {
        if (!isContains(type))
            return;

        rateLimiter.remove(type);
    }

    public void removeAll() {
        rateLimiter.keySet().forEach(rateLimiter::remove);
    }

    public boolean tryAcquire(T type) {
        if (!isContains(type))
            add(type);

        return rateLimiter.get(type).tryAcquire();
    }

    public boolean isContains(@Nonnull T type) {
        return rateLimiter.containsKey(type);
    }
}
