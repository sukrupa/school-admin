package org.sukrupa.smallneeds;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.sukrupa.app.needs.SmallNeedsController;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasEntry;

@SuppressWarnings("unchecked")
public class SmallNeedsControllerTest {

    @Mock
    SmallNeedRepository smallNeedRepository;
    private HashMap<String, Object> studentModel = new HashMap<String, Object>();
    public SmallNeedsController controller;

    private HashMap<String, Object> model = new HashMap<String, Object>();

    @Before
    public void setUp() {
        initMocks(this);
        controller = new SmallNeedsController(smallNeedRepository);
    }

    @Test
    public void shouldDisplaySmallNeedsPage() {
        List<SmallNeed> smallNeedList = asList(mock(SmallNeed.class), mock(SmallNeed.class));
        when(smallNeedRepository.getList()).thenReturn(smallNeedList);

        String view = controller.list(model);

        assertThat(view, is("smallNeeds/smallNeedsList"));
        assertThat(model, hasEntry("smallNeedList", smallNeedList));
        assertThat(model, hasEntry("priority", 3));
    }

    @Test
    public void shouldAddSmallNeed() {
        ArgumentCaptor<SmallNeed> smallNeedCaptor = ArgumentCaptor.forClass(SmallNeed.class);

        String view = controller.create(1, "SchoolUniform", 5000L, "For Aarthi", model);

        assertThat(view, is("redirect:/smallneeds"));
        assertThat((String) model.get("message"), is("Added Successfully"));
        verify(smallNeedRepository).put(smallNeedCaptor.capture());
        assertThat(smallNeedCaptor.getValue().getItemName(), is("SchoolUniform"));
        assertThat(smallNeedCaptor.getValue().getCost(), is(5000L));
        assertThat(smallNeedCaptor.getValue().getComment(), is("For Aarthi"));
        assertThat(smallNeedCaptor.getValue().getPriority(), is(1));
    }
}
