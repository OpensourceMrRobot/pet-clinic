package org.rastalion.petclinic.repositories;

import org.rastalion.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepositorty extends CrudRepository<Pet, Long> {
}
