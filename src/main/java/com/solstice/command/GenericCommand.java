package com.solstice.command;

import java.io.Serializable;

import com.solstice.command.GenericCommandHeader;

@SuppressWarnings("unused")
public interface GenericCommand extends  Serializable {
    public GenericCommandHeader getHeader();
    public void setHeader(GenericCommandHeader header);
}

