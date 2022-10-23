package no.maddin;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static no.maddin.HasRecordComponentWithValue.hasRecordComponent;
import static org.hamcrest.Matchers.equalTo;

public class HasRecordComponentWithValueTest {

    @Test
    void validate() {
        record TestRecord(String sVal){};

        TestRecord r = new TestRecord("test");

        MatcherAssert.assertThat(r, hasRecordComponent("sVal", equalTo("test")));
    }
}
