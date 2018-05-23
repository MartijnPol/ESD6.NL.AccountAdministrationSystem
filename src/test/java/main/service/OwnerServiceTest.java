package main.service;

import main.dao.OwnerDao;
import main.domain.Address;
import main.domain.Owner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * @author Thom van de Pas on 16-5-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {

    private OwnerService ownerService;

    private Owner owner;

    @Mock
    private OwnerDao ownerDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.ownerService = new OwnerService();
        this.owner = new Owner("Herman", "de Schermman", new Date(), new Address());
        this.owner.setCitizenServiceNumber(1234567890L);
        this.ownerService.setOwnerDao(ownerDao);
        this.ownerService.createOrUpdate(this.owner);
    }

    @Test
    public void findOwnerByFirstNameLastNameAndCSNTest() {
        Owner unexpectedOwner = new Owner();

        this.ownerService.createOrUpdate(unexpectedOwner);

        assertNotEquals(unexpectedOwner, owner);

        when(this.ownerService.findByFullNameAndCSN(this.owner.getFirstName(), this.owner.getLastName(),
                this.owner.getCitizenServiceNumber())).thenReturn(this.owner);

        Owner foundOwner = this.ownerService.findByFullNameAndCSN(this.owner.getFirstName(),
                this.owner.getLastName(), this.owner.getCitizenServiceNumber());

        assertEquals(owner, foundOwner);
        assertNotEquals(unexpectedOwner, foundOwner);

        foundOwner = this.ownerService.findByFullNameAndCSN(this.owner.getLastName(),
                this.owner.getFirstName(), this.owner.getCitizenServiceNumber());

        assertEquals(null, foundOwner);

    }
}
