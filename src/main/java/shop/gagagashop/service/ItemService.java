package shop.gagagashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gagagashop.PagingConst;
import shop.gagagashop.domain.UploadFile;
import shop.gagagashop.domain.item.Bag;
import shop.gagagashop.domain.item.Clothes;
import shop.gagagashop.domain.item.EtcItem;
import shop.gagagashop.domain.item.Item;
import shop.gagagashop.dto.IdName.MemberIdName;
import shop.gagagashop.dto.item.ItemForm;
import shop.gagagashop.file.FileStore;
import shop.gagagashop.vo.ItemVO;
import shop.gagagashop.dto.item.ItemDTO;
import shop.gagagashop.repository.ItemRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @Transactional
    public Item saveItem(ItemForm itemForm) throws IOException {
        String itemKind = itemForm.getItemKind();
        String itemName = itemForm.getItemName();
        int price = itemForm.getPrice();
        int quantity = itemForm.getQuantity();
        UploadFile storeImageFile = (itemForm.getImageFile() == null) ? null : fileStore.storeFile(itemForm.getImageFile());
        if (itemKind.equals("Bag")) {
            return itemRepository.save(Bag.builder()
                    .itemName(itemName)
                    .price(price)
                    .stockQuantity(quantity)
                    .color(null)
                    .imageFile(storeImageFile)
                    .build());
        } else if (itemKind.equals("Clothes")) {
            return itemRepository.save(Clothes.builder()
                    .itemName(itemName)
                    .price(price)
                    .stockQuantity(quantity)
                    .imageFile(storeImageFile)
                    .build());
        } else {
            return itemRepository.save(EtcItem.builder()
                    .itemName(itemName)
                    .price(price)
                    .stockQuantity(quantity)
                    .imageFile(storeImageFile)
                    .build());
        }
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findById(itemId).get();
        findItem.changeItem(name, price, stockQuantity);
    }

    public List<MemberIdName> findAllIdName() {
        return itemRepository.findAll().stream().map(i -> new MemberIdName(i.getId(), i.getItemName()))
                .collect(Collectors.toList());

    }

    public List<ItemDTO> findAll() {
        List<Item> findItems = itemRepository.findAll();
        List<ItemDTO> dtoItems = findItems.stream().map(findItem -> ItemDTO.builder()
                .id(findItem.getId())
                .itemName(findItem.getItemName())
                .price(findItem.getPrice())
                .quantity(findItem.getStockQuantity())
                .build()).collect(Collectors.toList());
        return dtoItems;
    }

    public Page<ItemDTO> findAllOrderByNameAsc(Pageable pageable) {
        int pageIdx = pageable.getPageNumber()-1; // pageable 객체에서 현재 page 번호를 꺼낸다.
        pageable = PageRequest.of(pageIdx, PagingConst.PAGE_LIMIT, Sort.Direction.ASC, "itemName");
        return itemRepository.findAll(pageable).map(i -> ItemDTO.builder()
                .id(i.getId())
                .itemName(i.getItemName())
                .price(i.getPrice())
                .quantity(i.getStockQuantity())
                .build());
    }

    public ItemDTO findOne(Long itemId) {
        Optional<Item> optionalFindItem = itemRepository.findById(itemId);
        Item findItem = optionalFindItem.orElseThrow(
                () -> new IllegalArgumentException("해당 Item이 존재하지 않습니다.")
        );
        ItemDTO itemDTO = ItemDTO.builder()
                .id(findItem.getId())
                .itemName(findItem.getItemName())
                .price(findItem.getPrice())
                .quantity(findItem.getStockQuantity())
                .imageFile(new UploadFile(findItem.getUploadFileName(), findItem.getStoreFileName()))
                .build();
        return itemDTO;
    }

    public ItemVO findMaxSales() {
        Item findItem = itemRepository.findMaxSales();
        ItemVO itemVO = new ItemVO();
        itemVO.setId(findItem.getId());
        itemVO.setItemName(findItem.getItemName());
        itemVO.setPrice(findItem.getPrice());
        return itemVO;
    }

    public List<ItemVO> findSecondToFifthSales() {
        return itemRepository.findSecondToFifthSales().stream()
                .map(i -> new ItemVO(i.getId(), i.getItemName(), i.getPrice()))
                .collect(Collectors.toList());
    }

    public void deleteById(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public Page<ItemDTO> findClothesOrderByNameAsc(Pageable pageable) {
        int pageIdx = pageable.getPageNumber()-1; // pageable 객체에서 현재 page 번호를 꺼낸다.
        pageable = PageRequest.of(pageIdx, PagingConst.PAGE_LIMIT, Sort.Direction.ASC, "itemName");
        return itemRepository.findClothes(pageable).map(i -> ItemDTO.builder()
                .id(i.getId())
                .itemName(i.getItemName())
                .price(i.getPrice())
                .quantity(i.getStockQuantity())
                .build());
    }

    public Page<ItemDTO> findBagOrderByNameAsc(Pageable pageable) {
        int pageIdx = pageable.getPageNumber()-1; // pageable 객체에서 현재 page 번호를 꺼낸다.
        pageable = PageRequest.of(pageIdx, PagingConst.PAGE_LIMIT, Sort.Direction.ASC, "itemName");
        return itemRepository.findBag(pageable).map(i -> ItemDTO.builder()
                .id(i.getId())
                .itemName(i.getItemName())
                .price(i.getPrice())
                .quantity(i.getStockQuantity())
                .build());
    }


    public Page<ItemDTO> findAccessoryOrderByNameAsc(Pageable pageable) {
        int pageIdx = pageable.getPageNumber()-1; // pageable 객체에서 현재 page 번호를 꺼낸다.
        pageable = PageRequest.of(pageIdx, PagingConst.PAGE_LIMIT, Sort.Direction.ASC, "itemName");
        return itemRepository.findAccessories(pageable).map(i -> ItemDTO.builder()
                .id(i.getId())
                .itemName(i.getItemName())
                .price(i.getPrice())
                .quantity(i.getStockQuantity())
                .build());
    }

    public Page<ItemDTO> findEtcOrderByNameAsc(Pageable pageable) {
        int pageIdx = pageable.getPageNumber()-1; // pageable 객체에서 현재 page 번호를 꺼낸다.
        pageable = PageRequest.of(pageIdx, PagingConst.PAGE_LIMIT, Sort.Direction.ASC, "itemName");
        return itemRepository.findEtcItem(pageable).map(i -> ItemDTO.builder()
                .id(i.getId())
                .itemName(i.getItemName())
                .price(i.getPrice())
                .quantity(i.getStockQuantity())
                .build());
    }

    public ItemDTO findById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) {
            throw new IllegalStateException("해당 아이템이 없습니다.");
        }
        Item item = itemRepository.findById(id).get();
        return ItemDTO.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .quantity(item.getStockQuantity())
                .price(item.getPrice())
                .imageFile(new UploadFile(item.getUploadFileName(), item.getStoreFileName()))
                .build();
    }
}
