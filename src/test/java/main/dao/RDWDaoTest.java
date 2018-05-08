package main.dao;

import main.domain.RDW;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RDWDaoTest {

    private RDW rdwData;

    @Mock
    private RDWDao rdwDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        rdwData = new RDW();
        rdwData.setKenteken("08-SK-PX");
        rdwData.setAantal_cilinders("4");
    }

    @Test
    public void findByLicensePlateTest() {
        when(rdwDao.findByLicensePlate(Matchers.eq("08-SK-PX"))).thenReturn(rdwData);
        when(rdwDao.findByLicensePlate(AdditionalMatchers.not(Matchers.eq("08-SK-PX")))).thenReturn(null);

        RDW rdwDataByLicensePlate = rdwDao.findByLicensePlate("08-SK-PX");
        RDW emptyRDWDataByLicensePlate = rdwDao.findByLicensePlate("08-PX-SK");

        assertThat(rdwDataByLicensePlate.getKenteken(), is("08-SK-PX"));
        assertThat(rdwDataByLicensePlate.getAantal_cilinders(), is("4"));
        assertThat(emptyRDWDataByLicensePlate, is(nullValue()));
    }
}
