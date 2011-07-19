package org.sukrupa.bigneeds;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.sukrupa.app.needs.BigNeedsController;
import org.sukrupa.app.needs.SmallNeedsController;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasEntry;

@SuppressWarnings("unchecked")
public class BigNeedsControllerTest {

    @Mock
    BigNeedRepository bigNeedRepository;
    private HashMap<String, Object> studentModel = new HashMap<String, Object>();
    public BigNeedsController controller;

    private HashMap<String, Object> model = new HashMap<String, Object>();

    @Before
    public void setUp() {
        initMocks(this);
        controller = new BigNeedsController(bigNeedRepository);
    }

    @Test
    public void shouldDisplayBigNeedsPage() {
        HttpSession session = mock(HttpSession.class);

        assertThat(controller.list(model,session), is("bigNeeds/bigNeedsList"));
    }

    @Test
    public void shouldRetrieveBigNeedListToModel() {
        HttpSession session = mock(HttpSession.class);
        List<BigNeed> bigNeedList = mock(List.class);
        when(bigNeedRepository.getList()).thenReturn(bigNeedList);
        String view = controller.list(model, session);
        Assert.assertThat(view, is("bigNeeds/bigNeedsList"));
        assertThat(model, hasEntry("bigNeedList", bigNeedList));
    }

    @Test
    public void shouldCreateABigNeed() {
        HttpSession session = mock(HttpSession.class);
        ArgumentCaptor<BigNeed> bigNeedCaptor = ArgumentCaptor.forClass(BigNeed.class);
        String view = controller.create("1", "sample", "60000", "0.0",session, model);
        verify(bigNeedRepository).addNeed(bigNeedCaptor.capture(), eq(1));
        assertThat(bigNeedCaptor.getValue().getItemName(), is("sample"));
        assertThat(bigNeedCaptor.getValue().getCost(), is((double)60000));
    }

    @Test
    public void shouldDeleteABigNeed() {
        BigNeed bigNeed = mock(BigNeed.class);
        HttpSession session=new MockHttpSession();
        when(bigNeedRepository.getNeedById(123)).thenReturn(bigNeed);
        when(bigNeed.getItemName()).thenReturn("Banana");
        String view = controller.delete(123, model, session);
        verify(bigNeedRepository).delete(bigNeed);
    }





    @Test
    public void shouldSaveAnEditedBigNeed() {
        BigNeed bigNeed = mock(BigNeed.class);
        when(bigNeedRepository.getNeedById(123)).thenReturn(bigNeed);
        when(bigNeed.getItemName()).thenReturn("Banana");
        String view = controller.saveEdit("1", 123, "Forks", "9001","0.0", model);
        //controller.saveEdit(123, "Forks" , "9001" , model);
        assertThat(view, is("redirect:/bigneeds"));
        //assertThat(model, hasEntry("message", "Saved changes to Forks"));
        verify(bigNeedRepository).editNeed(bigNeed, 1);
    }
}
