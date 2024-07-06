package com.hierynomus.mssmb2.snapshot;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * [MS-SMB2] 2.2.32.2 SMB2_SNAPSHOT_ARRAY - Represents single snapshot information as specified by [MS-CIFS] 2.2.1.1.2 GMT Token.
 */
public class SnapshotInformation implements Comparable<SnapshotInformation> {
    /**
     * [MS-CIFS] 2.2.1.1.2 GMT Token date-time formatter.
     */
    private static final DateTimeFormatter GMT_TOKEN_FORMATTER = DateTimeFormatter.ofPattern("'@GMT-'yyyy.MM.dd-HH.mm.ss").withZone(ZoneId.of("GMT"));

    private final Instant timestamp;


    public SnapshotInformation(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static SnapshotInformation fromSnapshotName(String snapshotName) {
        return new SnapshotInformation(Instant.from(GMT_TOKEN_FORMATTER.parse(snapshotName)));
    }

    /**
     * @return The timestamp of the snapshot in GMT.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return GMT_TOKEN_FORMATTER.format(timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnapshotInformation that = (SnapshotInformation) o;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(timestamp);
    }

    @Override
    public int compareTo(SnapshotInformation other) {
        return timestamp.compareTo(other.timestamp);
    }
}
