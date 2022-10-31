package tokyo.ramune.farmmc.game.crop;

import org.bukkit.Location;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NameTagHandler {
    private final Location location;
    private List<NameTag> nameTags = new ArrayList<>();

    public NameTagHandler(@Nonnull Location location) {
        this.location = location;
    }

    public void setNameTag(int index, @Nonnull String text) {
        if (existsLine(index)) {
            Objects.requireNonNull(getNameTag(index)).setText(text);
            return;
        }

        System.out.println(index + " ");
        nameTags.add(new NameTag(text, index, location));
    }

    public void removeNameTag(int index) {
        if (!existsLine(index))
            return;

        NameTag nameTag = Objects.requireNonNull(getNameTag(index));

        nameTag.remove();
        nameTags.remove(nameTag);
    }

    public void removeNameTags() {
        nameTags.forEach(NameTag::remove);
        nameTags = new ArrayList<>();
    }

    public List<NameTag> getNameTags() {
        return nameTags;
    }

    public void setNameTags(@Nonnull List<String> nameTagLines) {
        removeNameTags();

        int index = 0;
        for (String nameTagLine : nameTagLines) {
            setNameTag(index, nameTagLine);
            index++;
        }
    }

    @Nullable
    public NameTag getNameTag(int index) {
        for (NameTag nameTag : nameTags) {
            if (nameTag.getIndex() == index)
                return nameTag;
        }
        return null;
    }

    public void setVisible(boolean visible) {
        nameTags.forEach(nameTag -> nameTag.setVisible(visible));
    }

    public void setVisible(int index, boolean visible) {
        Objects.requireNonNull(getNameTag(index)).setVisible(visible);
    }

    public boolean existsLine(int index) {
        for (NameTag nameTag : nameTags) {
            if (nameTag.getIndex() == index)
                return true;
        }
        return false;
    }
}
