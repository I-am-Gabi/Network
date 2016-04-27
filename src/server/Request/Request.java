package server.Request;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Request extends BufferedOutputStream{
	
	
	public Request(OutputStream arg0) {
		super(arg0);
	}

	@Override
	public synchronized void flush() throws IOException {
		super.flush();
	}

	@Override
	public synchronized void write(byte[] b, int off, int len) throws IOException {
		super.write(b, off, len);
	}

	@Override
	public synchronized void write(int b) throws IOException {
		super.write(b);
	}


}
