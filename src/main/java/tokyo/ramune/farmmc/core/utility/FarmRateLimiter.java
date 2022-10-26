package tokyo.ramune.farmmc.core.utility;

import com.google.common.util.concurrent.RateLimiter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FarmRateLimiter<T> {
    private static List<FarmRateLimiter> instancedRateLimiters = new ArrayList<>();
    private final double permitsPerSeconds;
    private Map<T, RateLimiter> rateLimiters = new HashMap<>();

    public FarmRateLimiter(double permitsPerSeconds) {
        this.permitsPerSeconds = permitsPerSeconds;

        instancedRateLimiters.add(this);
    }

    public static void removeInstanced() {
        instancedRateLimiters.forEach(FarmRateLimiter::removeAll);
        instancedRateLimiters = new ArrayList<>();
    }

    public void add(@Nonnull T type) {
        if (isContains(type))
            return;

        rateLimiters.put(type, RateLimiter.create(permitsPerSeconds));
    }

    public void remove(@Nonnull T type) {
        if (!isContains(type))
            return;

        rateLimiters.remove(type);
    }

    public void removeAll() {
        rateLimiters = new HashMap<>();
    }

    public boolean tryAcquire(T type) {
        if (!isContains(type))
            add(type);

        return rateLimiters.get(type).tryAcquire();
    }

    public boolean isContains(@Nonnull T type) {
        return rateLimiters.containsKey(type);
    }
}
