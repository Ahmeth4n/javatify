package com.magnakod.emulator;

public interface ProtoActions {
    byte[] create();
    Object parse(byte[] parseObject);
}
