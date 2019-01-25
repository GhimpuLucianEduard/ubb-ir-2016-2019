/********************************************************************************
** Form generated from reading UI file 'exersez_oop_cu_gui.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_EXERSEZ_OOP_CU_GUI_H
#define UI_EXERSEZ_OOP_CU_GUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_exersez_oop_cu_guiClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *exersez_oop_cu_guiClass)
    {
        if (exersez_oop_cu_guiClass->objectName().isEmpty())
            exersez_oop_cu_guiClass->setObjectName(QStringLiteral("exersez_oop_cu_guiClass"));
        exersez_oop_cu_guiClass->resize(600, 400);
        menuBar = new QMenuBar(exersez_oop_cu_guiClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        exersez_oop_cu_guiClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(exersez_oop_cu_guiClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        exersez_oop_cu_guiClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(exersez_oop_cu_guiClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        exersez_oop_cu_guiClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(exersez_oop_cu_guiClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        exersez_oop_cu_guiClass->setStatusBar(statusBar);

        retranslateUi(exersez_oop_cu_guiClass);

        QMetaObject::connectSlotsByName(exersez_oop_cu_guiClass);
    } // setupUi

    void retranslateUi(QMainWindow *exersez_oop_cu_guiClass)
    {
        exersez_oop_cu_guiClass->setWindowTitle(QApplication::translate("exersez_oop_cu_guiClass", "exersez_oop_cu_gui", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class exersez_oop_cu_guiClass: public Ui_exersez_oop_cu_guiClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_EXERSEZ_OOP_CU_GUI_H
