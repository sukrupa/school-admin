package org.sukrupa.smallneeds;


import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.sukrupa.app.needs.SmallNeedsController;
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
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
        HttpSession session = mock(HttpSession.class);
        List<SmallNeed> smallNeedList = asList(mock(SmallNeed.class), mock(SmallNeed.class));
        when(smallNeedRepository.getList()).thenReturn(smallNeedList);

        String view = controller.list(model, session);

        assertThat(view, is("smallNeeds/smallNeedsList"));
        assertThat(model, hasEntry("smallNeedList", smallNeedList));
        assertThat(model, hasEntry("priority", 3));
    }

    @Test
    public void shouldDisplaySuccessMessageWhenAvailable() {
        HttpSession session = new MockHttpSession();
        session.setAttribute("message", "some message");

        String view = controller.list(model, session);

        assertThat(model, hasEntry("shouldDisplayMessage", true));
        assertThat(model, hasEntry("message", "some message"));
        assertThat(session.getAttribute("message"), is(nullValue()));
    }

    @Test
    public void shouldNotDisplaySuccessMessageWhenUnavailable() {
        HttpSession session = new MockHttpSession();

        String view = controller.list(model, session);

        assertThat(model, hasEntry("shouldDisplayMessage", false));
    }


    @Test
    public void shouldCreateSmallNeed(){
        ArgumentCaptor<SmallNeed> smallNeedCaptor = ArgumentCaptor.forClass(SmallNeed.class);
        HttpSession session = new MockHttpSession();
        String view = controller.create(1,"Water Heater","1000","Waterheater",session,model);
        verify(smallNeedRepository).addNeed((smallNeedCaptor.capture()), 1);
        assertThat(smallNeedCaptor.getValue().getItemName(), is("Water Heater"));
    }
    @Test
    public void shouldDeleteSmallNeed(){

        SmallNeed smallNeed=mock(SmallNeed.class);
        HttpSession session=new MockHttpSession();
        when(smallNeedRepository.getNeedById(123)).thenReturn(smallNeed);
        controller.delete(123, model,session);
        verify(smallNeedRepository).delete(smallNeed);

    }

    @Test
    public void shouldEditSmallNeed(){

        SmallNeed smallNeed=mock(SmallNeed.class);
        HttpSession session=new MockHttpSession();
        when(smallNeedRepository.getNeedById(111)).thenReturn(smallNeed);
        String view=controller.saveEdit("1",111,"Cooler","10000", "Cooler is essential ", model);
        verify(smallNeedRepository).addNeed(smallNeed, 1);

    }
}
