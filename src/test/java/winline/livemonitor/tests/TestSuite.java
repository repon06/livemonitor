package winline.livemonitor.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestRemoveMonitor.class, TestAddMonitor.class, TestDragNDrop.class, TestPlayList.class})
// тут все тесты @Suite.SuiteClasses({ TestRemoveMonitor.class, TestAddMonitor.class, TestFilterEvents.class, TestDragNDrop.class,TestRenameMonitor.class, TestPlayList.class })

public class TestSuite {
}