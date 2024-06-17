package com.cbastian.apicat.resources.usecase.service.uuid;

import java.util.UUID;
public class UuidManagerService {

    private UUID uuid;

    public UuidManagerService() {
        uuid = UUID.randomUUID();
    }
    public String  getUuid() {
        return uuid.toString();
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    @Override
    public String toString() {
        return "GenerateUUID [uuid=" + uuid + "]";
    }
}