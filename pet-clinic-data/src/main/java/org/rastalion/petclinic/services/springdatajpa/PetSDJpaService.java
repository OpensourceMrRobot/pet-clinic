package org.rastalion.petclinic.services.springdatajpa;

import org.rastalion.petclinic.model.Pet;
import org.rastalion.petclinic.repositories.PetRepositorty;
import org.rastalion.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepositorty petRepositorty;

    public PetSDJpaService(PetRepositorty petRepositorty) {
        this.petRepositorty = petRepositorty;
    }

    @Override
    public Set<Pet> findAll() {

        Set<Pet> pets = new HashSet<>();
        petRepositorty.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepositorty.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepositorty.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        petRepositorty.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        petRepositorty.deleteById(id);
    }
}
