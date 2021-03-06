package jackbox.example.methodannotation;

import jackbox.JackboxRecorder;
import jackbox.MethodRecording;
import jackbox.persistence.json.JSONPersister;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;

import static org.fest.assertions.Assertions.assertThat;

public class MethodAnnotationDemoTest {

    private ByteArrayOutputStream sysout;

    @Test
    public void shouldCallDaoDuringRecording() throws Exception {
        MethodAnnotationDemoApp.main(new String[0]);
        assertThat(getSysout()).contains("MethodAnnotationDemoDao");
    }

    @Test
    public void shouldNotCallDaoDuringPlayback() throws Exception {
        MethodAnnotationDemoApp.main(new String[0]);
        MethodRecording recording = JackboxRecorder.getLastCompletedRecording();

        JSONPersister persister = new JSONPersister();
        StringWriter serializedRecording = new StringWriter();
        persister.persistToWriter(recording, serializedRecording);

        captureSysout();
        recording = persister.readFromReader(new StringReader(serializedRecording.toString()));

        recording.replay();
        assertThat(getSysout())
            .contains("MethodAnnotationDemoService")
            .excludes("MethodAnnotationDemoDao");
    }

    private String getSysout() {
        return new String(sysout.toByteArray());
    }

    @Before
    public void captureSysout() {
        sysout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysout));
    }

}
