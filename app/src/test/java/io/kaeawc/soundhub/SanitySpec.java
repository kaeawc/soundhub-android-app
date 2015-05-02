package io.kaeawc.soundhub;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class SanitySpec {

    @Test
    public void getTimestampSecondPrecision() {
        assertThat(1 + 1).isEqualTo(2);
    }
}
