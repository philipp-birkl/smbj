package com.hierynomus.mssmb2.snapshot;

import com.hierynomus.protocol.commons.Charsets;
import com.hierynomus.protocol.commons.buffer.Buffer;
import com.hierynomus.smb.SMBBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * [MS-SMB2] 2.2.32.2 FSCTL_SRV_ENUMERATE_SNAPSHOTS response.
 */
public class EnumerateSnapshotResponse {
    private long numberOfSnapshots;
    private long numberOfSnapshotsReturned;
    private long snapshotArraySize;
    private List<SnapshotInformation> snapshots;

    public EnumerateSnapshotResponse() {
    }

    public EnumerateSnapshotResponse(long numberOfSnapshots, long numberOfSnapshotsReturned, long snapshotArraySize, List<SnapshotInformation> snapshots) {
        this.numberOfSnapshots = numberOfSnapshots;
        this.numberOfSnapshotsReturned = numberOfSnapshotsReturned;
        this.snapshotArraySize = snapshotArraySize;
        this.snapshots = snapshots;
    }

    public long getNumberOfSnapshots() {
        return numberOfSnapshots;
    }

    public long getNumberOfSnapshotsReturned() {
        return numberOfSnapshotsReturned;
    }

    public long getSnapshotArraySize() {
        return snapshotArraySize;
    }

    public List<SnapshotInformation> getSnapshots() {
        return snapshots;
    }

    public void read(SMBBuffer in) throws Buffer.BufferException {
        this.numberOfSnapshots = in.readUInt32();
        this.numberOfSnapshotsReturned = in.readUInt32();
        this.snapshotArraySize = in.readUInt32();
        this.snapshots = new ArrayList<>();

        for (long i = 0; i < this.numberOfSnapshotsReturned; i++) {
            this.snapshots.add(SnapshotInformation.fromSnapshotName(in.readNullTerminatedString(Charsets.UTF_16)));
        }
    }
}
