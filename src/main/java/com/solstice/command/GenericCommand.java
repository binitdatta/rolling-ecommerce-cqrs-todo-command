package com.solstice.command;

@SuppressWarnings("unused")
public interface GenericCommand extends  Serializable {
    public GenericCommandHeader getHeader();
    public void setHeader(GenericCommandHeader header);
}

