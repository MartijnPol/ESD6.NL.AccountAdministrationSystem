package main.dao;

import main.domain.RDWFuel;
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
public class RDWFuelDaoTest {

    private RDWFuel rdwData;

    @Mock
    private RDWFuelDao rdwDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        rdwData = new RDWFuel();
        rdwData.setKenteken("08-SK-PX");
        rdwData.setBrandstof_omschrijving("Benzine");
    }

    @Test
    public void findByLicensePlateTest() {
        when(rdwDao.findByLicensePlate(Matchers.eq("08-SK-PX"))).thenReturn(rdwData);
        when(rdwDao.findByLicensePlate(AdditionalMatchers.not(Matchers.eq("08-SK-PX")))).thenReturn(null);

        RDWFuel rdwDataByLicensePlate = rdwDao.findByLicensePlate("08-SK-PX");
        RDWFuel emptyRDWDataByLicensePlate = rdwDao.findByLicensePlate("08-PX-SK");

        assertThat(rdwDataByLicensePlate.getKenteken(), is("08-SK-PX"));
        assertThat(rdwDataByLicensePlate.getBrandstof_omschrijving(), is("Benzine"));
        assertThat(emptyRDWDataByLicensePlate, is(nullValue()));
    }
}
