/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.material;

import static com.mycompany.mavenproject3.TranslationSvc.getText;
import com.mycompany.mavenproject3.view.GenericViewV2;
import com.vaadin.data.converter.StringToFloatConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import genericdao.GenericDaoImp;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.gridutil.cell.GridCellFilter;

/**
 *
 * @author fatih
 */
public class MaterialView extends GenericViewV2<Material> {

    @Inject
    MaterialDao dao;
    @Inject
    GenericDaoImp<MaterialClass> mtclassdao;
    @Inject
    GenericDaoImp<MaterialGroup> mtgroupdao;
    @Inject
    GenericDaoImp<com.mycompany.mavenproject3.material.Unit> unitdao;
    @Inject
    Material material;
    private TextField code = new TextField(getText("CODE")) ;
    private TextField gcode = new TextField(getText("GCODE")) ;
    private ComboBox<MaterialClass> materialClass = new ComboBox(getText("MATERIALCLASS"));
    private ComboBox<MaterialGroup> materialGroup = new ComboBox(getText("MATERIALGROUP"));
    private ComboBox<com.mycompany.mavenproject3.material.Unit> unit = new ComboBox(getText("UNIT"));
    private TextField taxrate =  new TextField(getText("TAXRATE")) ;
    private TextField netweight = new TextField(getText("NETWEIGHT")) ;
    private TextField grossweight = new TextField(getText("GROSSWEIGHT")) ;
    private TextField minOrderQuantity = new TextField(getText("MINORDERQUANTITY")) ;
    private TextField hierarchy = new TextField(getText("HIERARCHY")) ;
    private TextField previousCode = new TextField(getText("PREVIOUSCIDE")) ;
    private CheckBox dfx = new CheckBox(getText("DFX"));
    private TextField dfxdiscount = new TextField(getText("DFXDISCOUNT")) ;
    private TextField dfxMaxOrderQuantity = new TextField(getText("DFXMAXORDERQUANTITY")) ;
    private TextField dfxMinStockQuantity = new TextField(getText("DFXMINSTOCKQUANTITY")) ;
    private TextField dfxStockQuantity = new TextField(getText("DFXSTOCKQUANTITY")) ;
    
    @PostConstruct
    public void init() {
        this.setDao(dao);
        this.setObject(material);
        
        prepareDao();
        prepareCombo();
        prepareMisc();
        prepareLayout();
    }

    private void prepareDao() {
        mtclassdao.setType(MaterialClass.class);
        mtgroupdao.setType(MaterialGroup.class);
        unitdao.setType(Unit.class);
    }

    private void prepareCombo() {
        materialClass.setItems(mtclassdao.findAllWithTranslation());
        materialClass.setItemCaptionGenerator(e->e.getName());
        materialGroup.setItems(mtgroupdao.findAllWithTranslation());
        materialGroup.setItemCaptionGenerator(e->e.getName());
        unit.setItems(unitdao.findAllWithTranslation());
        unit.setItemCaptionGenerator(e -> e.getName());
    }

    private void prepareMisc() {
        prepareBinder();
        grid.setItems(dao.findAllByCompany());
        filter = new GridCellFilter(grid,Material.class);
    }

    private void prepareBinder() {
                binder.forField ( this.taxrate )
              .withNullRepresentation ( "" )
              .withConverter ( new StringToIntegerConverter ( Integer.valueOf ( 0 ), "integers only" ) )
               .bind ( Material:: getTaxrate, Material:: setTaxrate );
        
        binder.forField ( this.dfxdiscount )
              .withNullRepresentation ( "" )
              .withConverter ( new StringToIntegerConverter ( Integer.valueOf ( 0 ), "integers only" ) )
               .bind ( Material:: getDfxdiscount, Material:: setDfxdiscount );
        binder.forField ( this.netweight )
              .withNullRepresentation ( "" )
              .withConverter ( new StringToFloatConverter ( Float.valueOf ( 0 ), "integers only" ) )
               .bind ( Material:: getNetweight, Material:: setNetweight );
        binder.forField ( this.grossweight )
              .withNullRepresentation ( "" )
              .withConverter ( new StringToFloatConverter ( Float.valueOf ( 0 ), "integers only" ) )
              .bind ( Material:: getGrossweight, Material:: setGrossweight );
        
        binder.forField ( this.minOrderQuantity )
              .withNullRepresentation ( "" )
              .withConverter ( new StringToFloatConverter ( Float.valueOf ( 0 ), "integers only" ) )
              .bind ( Material:: getMinOrderQuantity, Material:: setMinOrderQuantity );
        binder.forField ( this.dfxMaxOrderQuantity )
              .withNullRepresentation ( "" )
              .withConverter ( new StringToFloatConverter ( Float.valueOf ( 0 ), "integers only" ) )
              .bind ( Material:: getDfxMaxOrderQuantity, Material:: setDfxMaxOrderQuantity );

        binder.forField ( this.dfxStockQuantity )
              .withNullRepresentation ( "" )
              .withConverter ( new StringToFloatConverter ( Float.valueOf ( 0 ), "integers only" ) )
              .bind ( Material:: getDfxStockQuantity, Material:: setDfxStockQuantity );

        binder.forField ( this.dfxMinStockQuantity )
              .withNullRepresentation ( "" )
              .withConverter ( new StringToFloatConverter ( Float.valueOf ( 0 ), "integers only" ) )
              .bind ( Material:: getDfxMinStockQuantity, Material:: setDfxMinStockQuantity );
        this.binder.bindInstanceFields(this);

    }

    private void prepareLayout() {
                HorizontalLayout mainlayout = new HorizontalLayout();
                VerticalLayout column1 = new VerticalLayout();
                VerticalLayout column2 = new VerticalLayout();
                column1.addComponents(code,gcode,materialClass,materialGroup,unit,taxrate,netweight,grossweight
                );
                column2.addComponents(minOrderQuantity,hierarchy,
                previousCode,dfxdiscount,
                dfxMaxOrderQuantity,
                dfxMinStockQuantity,
                dfxStockQuantity,dfx);
                mainlayout.addComponents(column1,column2);
                this.addComponents(mainlayout,
                buttons,grid);

    }

}
