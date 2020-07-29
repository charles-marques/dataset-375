package org.openscada.protocols.arduino.messages;

import org.openscada.protocols.arduino.CommandCode;

public class WriteRequestMessage extends CommonMessage
{
    private final short index;

    private final Object data;

    public WriteRequestMessage ( final int sequence, final short index, final Object data )
    {
        super ( sequence, CommandCode.REQUEST_WRITE );
        this.index = index;
        this.data = data;
    }

    public Object getData ()
    {
        return this.data;
    }

    public short getIndex ()
    {
        return this.index;
    }

}
