package org.rastalion.petclinic.services;

import org.rastalion.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
