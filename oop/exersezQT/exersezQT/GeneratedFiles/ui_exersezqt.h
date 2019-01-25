/********************************************************************************
** Form generated from reading UI file 'exersezqt.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_EXERSEZQT_H
#define UI_EXERSEZQT_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QSpinBox>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_exersezQTClass
{
public:
    QWidget *centralWidget;
    QSpinBox *spinBox;
    QTextEdit *textEdit;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *exersezQTClass)
    {
        if (exersezQTClass->objectName().isEmpty())
            exersezQTClass->setObjectName(QStringLiteral("exersezQTClass"));
        exersezQTClass->resize(600, 400);
        centralWidget = new QWidget(exersezQTClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        spinBox = new QSpinBox(centralWidget);
        spinBox->setObjectName(QStringLiteral("spinBox"));
        spinBox->setGeometry(QRect(50, 70, 71, 31));
        textEdit = new QTextEdit(centralWidget);
        textEdit->setObjectName(QStringLiteral("textEdit"));
        textEdit->setGeometry(QRect(280, 130, 104, 87));
        exersezQTClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(exersezQTClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 600, 26));
        exersezQTClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(exersezQTClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        exersezQTClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(exersezQTClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        exersezQTClass->setStatusBar(statusBar);

        retranslateUi(exersezQTClass);
        QObject::connect(spinBox, SIGNAL(valueChanged(QString)), textEdit, SLOT(setText(QString)));

        QMetaObject::connectSlotsByName(exersezQTClass);
    } // setupUi

    void retranslateUi(QMainWindow *exersezQTClass)
    {
        exersezQTClass->setWindowTitle(QApplication::translate("exersezQTClass", "exersezQT", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class exersezQTClass: public Ui_exersezQTClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_EXERSEZQT_H
